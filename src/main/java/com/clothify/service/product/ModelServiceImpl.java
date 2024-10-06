package com.clothify.service.product;

import com.clothify.domain.product.*;
import com.clothify.payload.request.product.VariantRequest;
import com.clothify.repository.InventoryRepository;
import com.clothify.repository.ModelRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModelServiceImpl implements ModelService {
  private final ModelRepository modelRepository;
  private final InventoryRepository inventoryRepository;

  @Override
  @Transactional
  public Model createModelWithOutVariant(Product product) {
    Model model = new Model();
    model.setProduct(product);
    model.setPublished(product.isPublished());
    model.setPrice(product.getPrice());
    model.setBasePrice(product.getBasePrice());
    return modelRepository.save(model);
  }

  @Override
  public Model createModel(
      Product product, VariantRequest variant, List<ProductOption> productOptions) {
    Model model = new Model();
    model.setProduct(product);
    model.setPrice(variant.getPrice() != null ? variant.getPrice() : product.getPrice());
    model.setBasePrice(
        variant.getBasePrice() != null ? variant.getBasePrice() : product.getBasePrice());
    model.setPublished(variant.isPublished());
    for (ProductOption productOption : productOptions) {
      ProductOptionCombined productOptionCombined =
          variant.getDetails().get(productOption.getOptionId());
      if (productOptionCombined != null) {
        if (productOptionCombined.getValue().equals(productOption.getValue())) {
          productOptionCombined.setProductOptionId(productOption.getId());
          productOptionCombined.setOptionId(productOption.getOptionId());
        }
      }
    }
    model.setDetails(variant.getDetails());
    return modelRepository.save(model);
  }

  @Override
  public List<Model> findAllByProductId(UUID productId, Boolean isFetchInventory) {
    if (isFetchInventory)
      return modelRepository.findAllByProductId(
          productId, ModelEntityGraph.____().inventory().____.____());
    return modelRepository.findAllByProductId(productId, ModelEntityGraph.NOOP);
  }

  @Override
  @Transactional
  public void deleteByProductId(UUID productId) {
    inventoryRepository.deleteAllByProductId(productId);
    modelRepository.deleteAllByProductId(productId);
  }
}
