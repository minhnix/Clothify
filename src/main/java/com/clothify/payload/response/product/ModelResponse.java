package com.clothify.payload.response.product;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelResponse {
  private Long productId;
  private Long modelId;
  private Long basePrice;
  private Long price;
  private Long stock;
  private String name;
  private boolean isPublished;
  private String productName;
  private List<ProductOptionCombinedResponse> productOptions;
}
