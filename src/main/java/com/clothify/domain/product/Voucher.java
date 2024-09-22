package com.clothify.domain.product;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.clothify.domain.enumuration.DiscountType;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(
    name = "vouchers",
    indexes = {
      @Index(name = "index_voucher_code", columnList = "voucher_code"),
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Voucher extends AbstractAuditing {

  @Id
  @GeneratedValue
  @Column(name = "voucher_id")
  private UUID id;

  @Column(name = "voucher_name", columnDefinition = "TEXT")
  private String name;

  @Column(name = "voucher_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private DiscountType type;

  @Column(name = "voucher_code", unique = true, nullable = false)
  private String code;

  @Column(name = "voucher_description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "voucher_image", columnDefinition = "TEXT")
  private String image;

  @Column(name = "voucher_condition", columnDefinition = "TEXT[]")
  @Type(ListArrayType.class)
  private List<String> applyForCategory;

  @Column(nullable = false)
  private Timestamp startDate;

  @Column(nullable = false)
  private Timestamp endDate;

  @Column(name = "voucher_value", nullable = false)
  private Long value;

  private Long voucherMaxValue;

  @Column(nullable = false)
  private Long maximumTotalUsage;

  private Long maxUsePerUser;
  private Long minOrderValue;
  private Long usedCount;
  private boolean isActive;
}
