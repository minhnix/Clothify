package com.clothify.domain.product;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "inventories")
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
