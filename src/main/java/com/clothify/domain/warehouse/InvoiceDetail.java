package com.clothify.domain.warehouse;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "invoice_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetail extends AbstractAuditing {
  @Id @GeneratedValue private UUID id;

  @Type(JsonType.class)
  @Column(columnDefinition = "JSONB")
  private ModelItem modelItem;

  private Long quantity;
  private Long price;
  private Long totalPrice;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "invoice_id", nullable = false)
  private Invoice invoice;
}
