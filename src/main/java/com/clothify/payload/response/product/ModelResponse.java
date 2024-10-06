package com.clothify.payload.response.product;

import com.clothify.domain.product.ProductOptionCombined;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ModelResponse {
  private UUID productId;
  private UUID modelId;
  private Long basePrice;
  private Long price;
  private Long stock;
  private String name;
  private boolean isPublished;
  private Map<UUID, ProductOptionCombined> details;

  private ExtInfo extInfo;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ExtInfo {
    private List<Integer> index;
  }
}
