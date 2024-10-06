package com.clothify.payload.request.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
  @NotNull private UUID categoryId;
  @NotNull private UUID brandId;
  @NotNull private String name;
  private String description;
  @Valid private List<AttributeDTO> attributes;
  private Long basePrice;
  @NotNull private Long price;
  private Long quantity;
  private String image;
  @Valid private List<ProductOptionsDTO> options;
  @Valid private List<VariantRequest> variants;
  private boolean isPublished = false;
  private boolean isVariant;
}
