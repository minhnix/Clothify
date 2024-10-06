package com.clothify.payload.request.product;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionsDTO {
  @NotNull private UUID optionId;
  private String optionName;
  @NotNull private List<String> values;
}
