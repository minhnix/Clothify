package com.clothify.service.product;

import com.clothify.domain.product.Model;
import com.clothify.domain.warehouse.Inventory;
import com.clothify.exception.BadRequestException;
import com.clothify.exception.NotFoundException;
import com.clothify.repository.InventoryRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
  private final InventoryRepository inventoryRepository;

  @Override
  @Transactional
  public Inventory createInventory(Model model, Long stock) {
    Inventory inventory = new Inventory();
    inventory.setModel(model);
    inventory.setStock(stock);
    inventory.setTotalSold(0L);
    return inventoryRepository.save(inventory);
  }

  @Override
  @Transactional
  public Inventory updateInventory(UUID id, Long newStock) {
    Inventory inventory =
        inventoryRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Inventory not found"));
    inventory.setStock(newStock);
    return inventoryRepository.save(inventory);
  }

  @Override
  @Transactional
  public Inventory processOrderAndSaveInventory(UUID modelId, Long quantity) {
    Inventory inventory =
        inventoryRepository
            .findById(modelId)
            .orElseThrow(() -> new NotFoundException("Inventory not found"));
    if (inventory.getStock() < quantity) {
      throw new BadRequestException("Stock is not enough");
    }
    inventoryRepository.atomicUpdate(modelId, quantity);
    return inventory;
  }
}
