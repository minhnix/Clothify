package com.clothify.controller;

import com.clothify.domain.constants.PageConstants;
import com.clothify.domain.product.Category;
import com.clothify.payload.response.ApiResponse;
import com.clothify.service.product.CategoryService;
import com.clothify.utils.PageableUtils;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping
  public ApiResponse findAll(
      @RequestParam(value = "page", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = PageConstants.DEFAULT_PAGE_SIZE) int size,
      @RequestParam(value = "sortBy", required = false) String sortBy,
      @RequestParam(value = "sortDir", required = false) String sortDir) {

    Pageable pageable = PageableUtils.getPageable(page, size, sortBy, sortDir);
    return new ApiResponse(
        categoryService.findAll(pageable), "All categories", HttpStatus.OK.value());
  }

  @GetMapping("/{id}")
  public ApiResponse findOne(@PathVariable("id") UUID id) {
    return new ApiResponse(categoryService.findCategoryById(id), "Category", HttpStatus.OK.value());
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public Category createCategory(@RequestBody @Valid Category category) {
    return categoryService.createCategory(category);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public Category updateCategory(
      @PathVariable("id") UUID id, @RequestBody @Valid Category category) {
    return categoryService.updateCategory(id, category.getName());
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCategory(@PathVariable("id") UUID id) {
    categoryService.deleteCategory(id);
  }
}
