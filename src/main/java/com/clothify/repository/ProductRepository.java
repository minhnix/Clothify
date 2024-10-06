package com.clothify.repository;

import com.clothify.domain.product.Product;
import com.clothify.repository.custom.CustomizedProductRepository;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends EntityGraphJpaRepository<Product, UUID>, CustomizedProductRepository {
    @Query("select p from Product p left join fetch p.category left join fetch p.brand where p.isPublished = :isPublished")
    Page<Product> findAllByPublished(Pageable pageable, @Param("isPublished") boolean isPublished);
    @Query("select p from Product p left join fetch p.category left join fetch p.brand")
    Page<Product> findAllByAdmin(Pageable pageable);

    Optional<Product> findById(UUID id, EntityGraph entityGraph);

    boolean existsByName(String name);
    @Modifying
    @Query(value = "DELETE FROM products p where p.product_id = :id", nativeQuery = true)
    void deleteById(@Param("id") UUID id);
}
