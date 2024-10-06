package com.clothify.service.product;

import com.clothify.domain.product.Product;
import com.clothify.domain.warehouse.InventoryProduct;
import com.clothify.exception.NotFoundException;
import com.clothify.repository.InventoryProductRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryProductServiceImpl implements InventoryProductService {
  private final InventoryProductRepository inventoryProductRepository;

  @Override
  public InventoryProduct createInventoryProduct(Product product, Long stock) {
    InventoryProduct inventoryProduct = new InventoryProduct();
    long stock1 = 0;
    if (stock != null && stock > 0) {
      stock1 = stock;
    }
    inventoryProduct.setStock(stock1);
    inventoryProduct.setLocation("unknown");
    inventoryProduct.setProduct(product);
    inventoryProduct.setTotalSold(0L);
    return inventoryProductRepository.save(inventoryProduct);
  }

  @Override
  public InventoryProduct updateInventoryProduct(
      UUID productId, Long stock, Long totalSold, String location) {
    InventoryProduct inventoryProduct =
        inventoryProductRepository
            .findById(productId)
            .orElseThrow(() -> new NotFoundException("Product inventory not found"));
    if (stock != null) inventoryProduct.setStock(stock);
    if (totalSold != null) inventoryProduct.setTotalSold(totalSold);
    if (location != null) inventoryProduct.setLocation(location);
    return inventoryProductRepository.save(inventoryProduct);
  }
}
