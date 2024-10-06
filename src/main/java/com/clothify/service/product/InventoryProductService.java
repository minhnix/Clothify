package com.clothify.service.product;

import com.clothify.domain.product.Product;
import com.clothify.domain.warehouse.InventoryProduct;
import java.util.UUID;

public interface InventoryProductService {
  InventoryProduct createInventoryProduct(Product product, Long stock);

  InventoryProduct updateInventoryProduct(
      UUID productId, Long stock, Long totalSold, String location);
}
