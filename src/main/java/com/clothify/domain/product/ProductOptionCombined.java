package com.clothify.domain.product;

import java.util.UUID;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionCombined {
  private UUID productOptionId;
  private UUID optionId;
  private String value;
}
