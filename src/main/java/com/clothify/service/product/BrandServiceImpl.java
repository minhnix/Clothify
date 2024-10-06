package com.clothify.service.product;

import com.clothify.domain.product.Brand;
import com.clothify.exception.NotFoundException;
import com.clothify.payload.response.PagedResponse;
import com.clothify.repository.BrandRepository;
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
public class BrandServiceImpl implements BrandService {
  private final BrandRepository brandRepository;

  @Override
  public Brand createBrand(Brand category) {
    return brandRepository.save(category);
  }

  @Override
  public Brand updateBrand(UUID id, String newName) {
    Brand category =
        brandRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Brand not found with id " + id));
    category.setName(newName);
    findBrandById(id);
    return brandRepository.save(category);
  }

  @Override
  public Brand findBrandById(UUID id) {
    return brandRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Brand not found with id " + id));
  }

  @Override
  public void deleteBrand(UUID id) {
    brandRepository.deleteById(id);
  }

  @Override
  public PagedResponse<Brand> findAll(Pageable pageable) {
    return new PagedResponse<>(brandRepository.findAll(pageable));
  }
}
