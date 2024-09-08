package com.clothify.domain.order;

import com.clothify.domain.product.Model;
import com.clothify.domain.product.Voucher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "order_details",
    uniqueConstraints = {
      @UniqueConstraint(
          columnNames = {
            "order_id",
            "model_id",
          })
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
  @Id @GeneratedValue private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JsonIgnore
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "model_id", nullable = false)
  private Model model;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "voucher_id")
  private Voucher voucher;

  private Long quantity;
  private Long price;
  private Long totalPrice;
}
