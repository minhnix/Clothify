package com.clothify.domain.warehouse;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.clothify.domain.product.Model;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "inventories")
@Data
public class Inventory extends AbstractAuditing {
  @Id private UUID id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Model model;

  @Column(name = "inventory_stock")
  private Long stock;

  @Column(name = "total_sold")
  private Long totalSold;
}
