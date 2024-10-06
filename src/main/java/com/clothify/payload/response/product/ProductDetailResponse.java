package com.clothify.payload.response.product;

import com.clothify.domain.product.*;
import com.clothify.exception.AppException;
import com.clothify.mapper.ModelMapper;
import com.clothify.payload.request.product.AttributeDTO;
import com.clothify.payload.request.product.ProductOptionsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductDetailResponse {
  private UUID id;
  private String name;
  private String slug;
  private Category category;
  private Brand brand;
  private String description;
  private String image;
  private Long price;
  private Long basePrice;
  private boolean isVariant;
  private boolean isPublish;
  private Long stock;
  private Long sold;
  private List<AttributeDTO> attributes;
  private Map<UUID, ProductOptionsDTO> productOptions;
  private List<ModelResponse> models;

  public static ProductDetailResponse toResponse(
      Product product, List<ProductOption> productOptions, List<Model> models) {
    return new ProductDetailResponseBuilder()
        .setProduct(product)
        .setProductOptions(productOptions)
        .setModels(models)
        .build();
  }

  public static class ProductDetailResponseBuilder {
    public ProductDetailResponseBuilder setProduct(Product product) {
      this.id = product.getId();
      this.name = product.getName();
      this.slug = product.getSlug();
      this.category = product.getCategory();
      this.description = product.getDescription();
      this.image = product.getImage();
      this.price = product.getPrice();
      this.basePrice = product.getBasePrice();
      this.isVariant = product.isVariant();
      this.isPublish = product.isPublished();
      this.stock = product.getInventoryProduct().getStock();
      this.sold = product.getInventoryProduct().getTotalSold();
      this.attributes = convertJsonToAttributes(product.getAttributes());
      return this;
    }

    public ProductDetailResponseBuilder setProductOptions(List<ProductOption> productOptions) {
      Map<UUID, ProductOptionsDTO> productOptionsMap = new HashMap<>();
      for (var productOption : productOptions) {
        if (productOptionsMap.containsKey(productOption.getOption().getId())) {
          ProductOptionsDTO productOptionsDTO =
              productOptionsMap.get(productOption.getOption().getId());
          productOptionsDTO.getValues().add(productOption.getValue());
        } else {
          List<String> values = new ArrayList<>();
          values.add(productOption.getValue());
          ProductOptionsDTO productOptionsDTO =
              new ProductOptionsDTO(
                  productOption.getOption().getId(), productOption.getOption().getName(), values);
          productOptionsMap.put(productOption.getOption().getId(), productOptionsDTO);
        }
      }
      this.productOptions = productOptionsMap;
      return this;
    }

    public ProductDetailResponseBuilder setModels(List<Model> models) {
      List<ModelResponse> modelResponses = new ArrayList<>();
      for (var model : models) {
        ModelResponse modelResponse = ModelMapper.INSTANCE.mapToModelResponse(model);
        modelResponse.setExtInfo(
            new ModelResponse.ExtInfo(
                getProductOptionIndex(model.getDetails(), this.productOptions)));
        modelResponses.add(modelResponse);
      }
      this.models = modelResponses;
      return this;
    }

    private List<AttributeDTO> convertJsonToAttributes(String json) {
      ObjectMapper objectMapper = new ObjectMapper();
      List<AttributeDTO> attributes;
      try {
        attributes = objectMapper.readValue(json, new TypeReference<>() {});
      } catch (JsonProcessingException e) {
        throw new AppException("Cannot convert json to attributes");
      }
      return attributes;
    }

    private List<Integer> getProductOptionIndex(
        Map<UUID, ProductOptionCombined> combinedMap,
        Map<UUID, ProductOptionsDTO> productOptionsList) {
      List<Integer> modelIndices = new ArrayList<>();
      for (Map.Entry<UUID, ProductOptionCombined> entry : combinedMap.entrySet()) {
        ProductOptionsDTO productOptionsDTO = productOptionsList.get(entry.getKey());
        if (productOptionsDTO == null) {
          throw new AppException("Product option not found");
        }
        int valueIndex = productOptionsDTO.getValues().indexOf(entry.getValue().getValue());
        if (valueIndex == -1) {
          throw new AppException("Product option value not found");
        }
        modelIndices.add(valueIndex);
      }
      return modelIndices;
    }
  }
}
