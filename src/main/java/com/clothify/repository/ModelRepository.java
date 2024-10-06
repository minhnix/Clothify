package com.clothify.repository;

import com.clothify.domain.product.Model;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModelRepository extends EntityGraphJpaRepository<Model, UUID> {
  List<Model> findAllByProductId(UUID productId, EntityGraph entityGraph);

  @Query(value = "SELECT m FROM Model m WHERE m.id = ?1")
  Optional<Model> findById(Long id);

  @Modifying
  @Query(value = "delete from Model m where m.product.id = :productId")
  void deleteAllByProductId(@Param("productId") UUID productId);
}
