package com.clothify.domain.order;

import com.clothify.domain.user.User;
import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.clothify.domain.enumuration.OrderStatus;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AbstractAuditing {
  @Id @GeneratedValue private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private User customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "staff_id", nullable = false)
  private User shipper;

  private Double totalPrice;
  private Double totalDiscount;
  private Double totalShippingCost;
  private Double totalCheckoutPrice;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private String address;
  private Double longitude;
  private Double latitude;

  private String note;
  private String trackingNumber;

  @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, optional = false)
  private Payment payment;

  @OneToMany(mappedBy = "order")
  private List<OrderDetail> orderDetails = new ArrayList<>();
}
