package com.clothify.service.product;

import com.clothify.domain.product.Model;
import com.clothify.domain.warehouse.Inventory;
import java.util.UUID;

public interface InventoryService {
  Inventory createInventory(Model model, Long stock);

  Inventory updateInventory(UUID id, Long newStock);

  Inventory processOrderAndSaveInventory(UUID modelId, Long quantity);
}
