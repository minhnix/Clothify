package com.clothify.service.product;

import com.clothify.domain.product.Model;
import com.clothify.domain.product.Product;
import com.clothify.domain.product.ProductOption;
import com.clothify.payload.request.product.VariantRequest;
import java.util.List;
import java.util.UUID;

public interface ModelService {
  Model createModelWithOutVariant(Product product);

  Model createModel(Product product, VariantRequest variants, List<ProductOption> productOptions);

  List<Model> findAllByProductId(UUID productId, Boolean isFetchInventory);

  void deleteByProductId(UUID productId);
}
