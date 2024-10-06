package com.clothify.payload.response.product;

import com.clothify.domain.product.Brand;
import com.clothify.domain.product.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SimpleProductResponse {
  private UUID id;
  private String name;
  private String slug;
  private Category category;
  private Brand brand;
  private String description;
  private String image;
  private Long price;
  private Long basePrice;
  private boolean isVariant;
}
