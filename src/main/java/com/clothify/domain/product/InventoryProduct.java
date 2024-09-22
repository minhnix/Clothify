package com.clothify.domain.product;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "inventory_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryProduct extends AbstractAuditing {
  @Id private UUID id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Product product;

  @Column(name = "inventory_location")
  private String location;

  @Column(name = "inventory_stock")
  private Long stock;

  @Column(name = "total_sold")
  private Long totalSold;
}
