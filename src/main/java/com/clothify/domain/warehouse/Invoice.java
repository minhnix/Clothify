package com.clothify.domain.warehouse;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends AbstractAuditing {
  @Id @GeneratedValue private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Supplier supplier;

  private String invoiceCode;
  private Long totalPrice;
  private String status;

  @OneToMany(mappedBy = "invoice")
  private List<InvoiceDetail> invoiceDetails = new ArrayList<>();
}
