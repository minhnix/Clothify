package com.clothify.domain.product;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute {
  private String name;
  private String description;
}
