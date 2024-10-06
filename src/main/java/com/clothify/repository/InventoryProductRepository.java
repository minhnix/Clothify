package com.clothify.repository;

import com.clothify.domain.warehouse.InventoryProduct;
import com.clothify.repository.custom.CustomizedInventoryProductRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface InventoryProductRepository
    extends EntityGraphJpaRepository<InventoryProduct, UUID>, CustomizedInventoryProductRepository {
  @Query(
      value = "delete from inventory_products where product_product_id = :id",
      nativeQuery = true)
  @Modifying
  void deleteByProductId(@Param("id") UUID id);

  @Modifying
  @Transactional
  @Query(
      value =
          "update inventory_products set inventory_stock = inventory_stock  - :amount, total_sold = total_sold + :amount where product_product_id = :id",
      nativeQuery = true)
  void atomicUpdate(@Param("id") Long id, @Param("amount") UUID amount);
}
