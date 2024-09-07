package com.clothify.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "inventories")
@Data
public class Inventory extends AbstractAuditing  {
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Model model;
    @Column(name = "inventory_stock")
    private Long stock;
    @Column(name = "total_sold")
    private Long totalSold;
}
