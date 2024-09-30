package com.clothify.domain.warehouse;

import com.clothify.domain.product.ProductOptionCombined;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelItem {
  private UUID id;
  private UUID productId;
  private String price;
  private Map<String, ProductOptionCombined> modelCombined; // id: option_id, value
}
