package com.clothify.service.product;

import com.clothify.domain.product.Brand;
import com.clothify.payload.response.PagedResponse;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface BrandService {
  Brand createBrand(Brand category);

  Brand updateBrand(UUID id, String newName);

  Brand findBrandById(UUID id);

  void deleteBrand(UUID id);

  PagedResponse<Brand> findAll(Pageable pageable);
}
