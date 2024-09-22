package com.clothify.payload.response.product;

import com.clothify.domain.product.Category;
import com.clothify.domain.product.Product;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductResponse {
  private UUID id;
  private String name;
  private String slug;
  private Category category;
  private String description;
  private String image;
  private Long basePrice;
  private Long price;
  private boolean isPublish;
  private Long stock;
  private Long sold;
  private Map<String, String> attributes;
  // TODO: add list productOptionResponse
  private List<ModelResponse> models;

  public static ProductResponse toResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .slug(product.getSlug())
        .category(product.getCategory())
        .description(product.getDescription())
        .image(product.getImage())
        .basePrice(product.getBasePrice())
        .price(product.getPrice())
        .isPublish(product.isPublished())
        .stock(product.getInventoryProduct().getStock())
        .sold(product.getInventoryProduct().getTotalSold())
        //        .attributes(
        //            product.getAttributes().entrySet().stream()
        //                .map(entry -> AttributeDTO.toAttributeDTO(entry.getKey(),
        // entry.getValue()))
        //                .toList())
        .build();
  }

  //  public static class ProductDetailResponseBuilder {
  //    public ProductDetailResponseBuilder setProduct(Product product) {
  //      this.id = product.getId();
  //      this.name = product.getName();
  //      this.slug = product.getSlug();
  //      this.category = product.getCategory();
  //      this.description = product.getDescription();
  //      this.image = product.getImage();
  //      this.price = product.getPrice();
  //      this.priceAfterDiscount = product.getPriceAfterDiscount();
  //      this.isVariant = product.isVariant();
  //      this.isPublish = product.isPublished();
  //      this.stock = product.getInventoryProduct().getStock();
  //      this.sold = product.getInventoryProduct().getTotalSold();
  //      this.attributes = product.getAttributes();
  //      return this;
  //    }

  //    public ProductDetailResponseBuilder setProductOptions(List<ProductOption> productOptions) {
  //      List<ProductOptionsDTO> productOptionsDTOS = new ArrayList<>();
  //      for (var productOption : productOptions) {
  //        if (!productOptionsDTOS.isEmpty()) {
  //          ProductOptionsDTO productOptionsDTO =
  //              productOptionsDTOS.get(productOptionsDTOS.size() - 1);
  //          if (productOptionsDTO.getName().equals(productOption.getOption().getName())) {
  //            productOptionsDTO.getValues().add(productOption.getValue());
  //          } else {
  //            List<String> values = new ArrayList<>();
  //            values.add(productOption.getValue());
  //            ProductOptionsDTO productOptionsDTO1 =
  //                new ProductOptionsDTO(
  //                    productOption.getId(), productOption.getOption().getName(), values);
  //            productOptionsDTOS.add(productOptionsDTO1);
  //          }
  //        } else {
  //          List<String> values = new ArrayList<>();
  //          values.add(productOption.getValue());
  //          ProductOptionsDTO productOptionsDTO =
  //              new ProductOptionsDTO(
  //                  productOption.getId(), productOption.getOption().getName(), values);
  //          productOptionsDTOS.add(productOptionsDTO);
  //        }
  //      }
  //      this.productOptions = productOptionsDTOS;
  //      return this;
  //    }
  //
  //    public ProductDetailResponseBuilder setModels(List<Model> models) {
  //      List<ModelResponse> modelResponses = new ArrayList<>();
  //      for (var model : models) {
  //        ModelResponse modelResponse = ModelMapper.INSTANCE.mapToModelResponse(model);
  //        modelResponse.setExtInfo(
  //            new ExtInfo(getProductOptionIndex(model.getName(), this.productOptions)));
  //        modelResponses.add(modelResponse);
  //      }
  //      this.models = modelResponses;
  //      return this;
  //    }
  //
  ////    private List<AttributeDTO> convertJsonToAttributes(String json) {
  ////      ObjectMapper objectMapper = new ObjectMapper();
  ////      List<AttributeDTO> attributes;
  ////      try {
  ////        attributes = objectMapper.readValue(json, new TypeReference<>() {});
  ////      } catch (JsonProcessingException e) {
  ////        throw new AppException("Cannot convert json to attributes");
  ////      }
  ////      return attributes;
  ////    }
  //
  //    private List<Integer> getProductOptionIndex(
  //        String modelName, List<ProductOptionsDTO> productOptionsList) {
  //      List<Integer> modelIndices = new ArrayList<>();
  //      String[] split = modelName.split(",");
  //      for (int i = 0; i < split.length; i++) {
  //        ProductOptionsDTO currentOption = productOptionsList.get(i);
  //        String value = split[i];
  //        int index = currentOption.getValues().indexOf(value);
  //        modelIndices.add(index);
  //      }
  //      return modelIndices;
  //    }
  //  }
}
