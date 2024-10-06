package com.clothify.controller;

import com.clothify.domain.constants.PageConstants;
import com.clothify.domain.product.Brand;
import com.clothify.payload.response.ApiResponse;
import com.clothify.service.product.BrandService;
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
@RequestMapping("/api/v1/brands")
public class BrandController {
  private final BrandService brandService;

  @GetMapping
  public ApiResponse findAll(
      @RequestParam(value = "page", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = PageConstants.DEFAULT_PAGE_SIZE) int size,
      @RequestParam(value = "sortBy", required = false) String sortBy,
      @RequestParam(value = "sortDir", required = false) String sortDir) {

    Pageable pageable = PageableUtils.getPageable(page, size, sortBy, sortDir);
    return new ApiResponse(brandService.findAll(pageable), "All categories", HttpStatus.OK.value());
  }

  @GetMapping("/{id}")
  public ApiResponse findOne(@PathVariable("id") UUID id) {
    return new ApiResponse(brandService.findBrandById(id), "Brand", HttpStatus.OK.value());
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public Brand createBrand(@RequestBody @Valid Brand brand) {
    return brandService.createBrand(brand);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public Brand updateBrand(@PathVariable("id") UUID id, @RequestBody @Valid Brand brand) {
    return brandService.updateBrand(id, brand.getName());
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBrand(@PathVariable("id") UUID id) {
    brandService.deleteBrand(id);
  }
}
