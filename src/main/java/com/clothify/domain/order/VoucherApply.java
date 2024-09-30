package com.clothify.domain.order;

import com.clothify.domain.enumuration.DiscountType;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherApply {
  private UUID id;
  private String name;
  private DiscountType type;
  private String code;
  private Long value;
  private String image;
}
