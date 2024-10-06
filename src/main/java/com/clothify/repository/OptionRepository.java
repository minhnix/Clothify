package com.clothify.repository;

import com.clothify.domain.product.Option;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;

import java.util.UUID;

public interface OptionRepository extends EntityGraphJpaRepository<Option, UUID> {
    boolean existsByName(String name);
}
