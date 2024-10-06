package com.clothify.repository;

import com.clothify.domain.warehouse.Inventory;
import com.clothify.repository.custom.CustomizedInventoryRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface InventoryRepository
    extends EntityGraphJpaRepository<Inventory, UUID>, CustomizedInventoryRepository {
  @Modifying
  @Query(
      value =
          "DELETE i FROM inventories i JOIN models m ON i.model_model_id = m.model_id where m.product_id = :productId",
      nativeQuery = true)
  void deleteAllByProductId(@Param("productId") UUID productId);

  @Modifying
  @Transactional
  @Query(
      value =
          "update inventories set inventory_stock = inventory_stock  - :amount, total_sold = total_sold + :amount where model_model_id = :id",
      nativeQuery = true)
  void atomicUpdate(@Param("id") UUID id, @Param("amount") Long amount);
}
