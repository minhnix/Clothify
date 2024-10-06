package com.clothify.service.product;

import com.clothify.domain.product.Product;
import com.clothify.payload.request.product.ProductRequest;
import com.clothify.payload.response.PagedResponse;
import com.clothify.payload.response.product.ProductDetailResponse;
import com.clothify.payload.response.product.SimpleProductResponse;
import java.util.UUID;

import com.clothify.security.CustomUserDetails;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  Product createProduct(ProductRequest productRequest);

  SimpleProductResponse publishProduct(UUID id);

  SimpleProductResponse unPublishProduct(UUID id);

  PagedResponse<SimpleProductResponse> findAllPublishProduct(Pageable pageable);

  PagedResponse<SimpleProductResponse> findAllUnPublishProduct(Pageable pageable);

  PagedResponse<SimpleProductResponse> findAllProduct(Pageable pageable);

  ProductDetailResponse findOneProduct(UUID id, CustomUserDetails user);

  SimpleProductResponse updateProduct(UUID productId, ProductRequest productRequest);

  void deleteProduct(UUID productId);
}
