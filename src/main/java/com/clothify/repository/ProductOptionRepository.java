package com.clothify.repository;

import com.clothify.domain.product.Product;
import com.clothify.domain.product.ProductOption;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductOptionRepository extends EntityGraphJpaRepository<ProductOption, UUID> {
  List<ProductOption> findAllByProduct(Product product, EntityGraph entityGraph);

  @Modifying
  @Query(
      value = "delete from product_options m where m.product_id = :productId",
      nativeQuery = true)
  void deleteAllByProductId(@Param("productId") UUID productId);
}
