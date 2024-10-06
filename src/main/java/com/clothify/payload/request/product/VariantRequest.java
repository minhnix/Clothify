package com.clothify.payload.request.product;

import com.clothify.domain.product.ProductOptionCombined;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantRequest {
  private boolean isPublished = true;
  private Long price;
  private Long basePrice;
  private Long quantity;
  @Valid @NotNull private Map<UUID, ProductOptionCombined> details; // id: option_id
}
