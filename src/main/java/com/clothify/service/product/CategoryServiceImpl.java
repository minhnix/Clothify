package com.clothify.service.product;

import com.clothify.domain.product.Category;
import com.clothify.exception.NotFoundException;
import com.clothify.payload.response.PagedResponse;
import com.clothify.repository.CategoryRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  @Override
  public Category createCategory(Category category) {
    log.info("create category");
    return categoryRepository.save(category);
  }

  @Override
  public Category updateCategory(UUID id, String newName) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Category not found with id " + id));
    category.setName(newName);
    return categoryRepository.save(category);
  }

  @Override
  @Transactional(readOnly = true)
  public Category findCategoryById(UUID id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Category not found with id " + id));
  }

  @Override
  public void deleteCategory(UUID id) {
    categoryRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public PagedResponse<Category> findAll(Pageable pageable) {
    return new PagedResponse<>(categoryRepository.findAll(pageable));
  }
}
