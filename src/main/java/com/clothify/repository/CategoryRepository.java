package com.clothify.repository;

import com.clothify.domain.product.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {}
