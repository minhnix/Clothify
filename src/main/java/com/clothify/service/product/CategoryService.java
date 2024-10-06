package com.clothify.service.product;

import com.clothify.domain.product.Category;
import com.clothify.payload.response.PagedResponse;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
  Category createCategory(Category category);

  Category updateCategory(UUID id, String newName);

  Category findCategoryById(UUID id);

  void deleteCategory(UUID id);

  PagedResponse<Category> findAll(Pageable pageable);
}
