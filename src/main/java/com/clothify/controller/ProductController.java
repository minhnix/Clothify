package com.clothify.controller;

import com.clothify.annotation.CurrentUser;
import com.clothify.domain.constants.PageConstants;
import com.clothify.domain.product.Product;
import com.clothify.payload.request.product.ProductRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.payload.response.PagedResponse;
import com.clothify.payload.response.product.SimpleProductResponse;
import com.clothify.security.CustomUserDetails;
import com.clothify.service.product.ProductService;
import com.clothify.utils.PageableUtils;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse createProduct(@RequestBody @Valid ProductRequest productRequest) {
    Product product = productService.createProduct(productRequest);
    return new ApiResponse(product, "Create product", HttpStatus.CREATED.value());
  }

  @PutMapping("/publish/{id}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ApiResponse publishProduct(@PathVariable("id") UUID id) {
    SimpleProductResponse product = productService.publishProduct(id);
    return new ApiResponse(product, "product published", HttpStatus.OK.value());
  }

  @PutMapping("/un-publish/{id}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ApiResponse unPublishProduct(@PathVariable("id") UUID id) {
    SimpleProductResponse product = productService.unPublishProduct(id);
    return new ApiResponse(product, "Product unpublished", HttpStatus.OK.value());
  }

  @GetMapping("/publish")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ApiResponse findAllPublishProduct(
      @RequestParam(value = "page", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = PageConstants.DEFAULT_PAGE_SIZE) int size,
      @RequestParam(value = "sortBy", required = false) String sortBy,
      @RequestParam(value = "sortDir", required = false) String sortDir) {

    Pageable pageable = PageableUtils.getPageable(page, size, sortBy, sortDir);
    PagedResponse<SimpleProductResponse> products = productService.findAllPublishProduct(pageable);
    return new ApiResponse(products, "Product published", HttpStatus.OK.value());
  }

  @GetMapping("/un-publish")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ApiResponse findAllUnPublishProduct(
      @RequestParam(value = "page", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = PageConstants.DEFAULT_PAGE_SIZE) int size,
      @RequestParam(value = "sortBy", required = false) String sortBy,
      @RequestParam(value = "sortDir", required = false) String sortDir) {

    Pageable pageable = PageableUtils.getPageable(page, size, sortBy, sortDir);
    PagedResponse<SimpleProductResponse> products =
        productService.findAllUnPublishProduct(pageable);
    return new ApiResponse(products, "Product unpublished", HttpStatus.OK.value());
  }

  @GetMapping("/{id}")
  public ApiResponse findOneProduct(
      @PathVariable("id") UUID id, @CurrentUser CustomUserDetails user) {
    return new ApiResponse(
        productService.findOneProduct(id, user), "Find one product", HttpStatus.OK.value());
  }

  @GetMapping
  public ApiResponse getAllProduct(
      @RequestParam(value = "page", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = PageConstants.DEFAULT_PAGE_SIZE) int size,
      @RequestParam(value = "sortBy", required = false) String sortBy,
      @RequestParam(value = "sortDir", required = false) String sortDir) {

    Pageable pageable = PageableUtils.getPageable(page, size, sortBy, sortDir);
    PagedResponse<SimpleProductResponse> products = productService.findAllPublishProduct(pageable);
    return new ApiResponse(products, "Product published", HttpStatus.OK.value());
  }

  @GetMapping("/admin")
  public ApiResponse getAllProductByAdmin(
      @RequestParam(value = "page", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = PageConstants.DEFAULT_PAGE_SIZE) int size,
      @RequestParam(value = "sortBy", required = false) String sortBy,
      @RequestParam(value = "sortDir", required = false) String sortDir) {

    Pageable pageable = PageableUtils.getPageable(page, size, sortBy, sortDir);
    PagedResponse<SimpleProductResponse> products = productService.findAllProduct(pageable);
    return new ApiResponse(products, "Product published", HttpStatus.OK.value());
  }

  @PutMapping("/{id}")
  public ApiResponse updateProduct(
      @PathVariable("id") UUID id, @RequestBody @Valid ProductRequest productRequest) {
    SimpleProductResponse product = productService.updateProduct(id, productRequest);
    return new ApiResponse(product, "Update product", HttpStatus.OK.value());
  }

  @DeleteMapping("/{id}")
  public ApiResponse updateProduct(@PathVariable("id") UUID id) {
    productService.deleteProduct(id);
    return ApiResponse.successWithoutDataAndMeta("deleted");
  }
}
