package com.clothify.repository.custom;

import com.clothify.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomizedProductRepository {
    Page<Product> findAllByPublished(Pageable pageable, boolean isPublished);
}
