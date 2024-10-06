package com.clothify.repository;

import com.clothify.domain.product.Brand;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, UUID> {}
