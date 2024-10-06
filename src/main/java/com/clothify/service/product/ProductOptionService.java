package com.clothify.service.product;

import com.clothify.domain.product.Product;
import com.clothify.domain.product.ProductOption;
import com.clothify.payload.request.product.ProductOptionsDTO;
import java.util.List;
import java.util.UUID;

public interface ProductOptionService {
  ProductOption createProductOption(UUID productId, UUID optionId, String value);

  void batchInsertProductOption(UUID productId, UUID optionId, List<String> values);

  List<ProductOption> batchInsertProductOption(
      UUID productId, List<ProductOptionsDTO> productOptions);

  List<ProductOption> findAllByProduct(Product product);

  void deleteAllByProductId(UUID productId);
}
