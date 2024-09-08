package com.clothify.payload.response.product;

import com.clothify.domain.product.Product;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductOptionCombinedResponse {
  private UUID id;
  private String name;

  public static ProductOptionCombinedResponse toResponse(Product product) {
    return ProductOptionCombinedResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .build();
  }
}
