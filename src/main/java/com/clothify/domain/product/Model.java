package com.clothify.domain.product;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(
    name = "models",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = {"product_id"}),
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Model extends AbstractAuditing {
  @Id
  @GeneratedValue
  @Column(name = "model_id")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "model_base_price", nullable = false)
  private Long basePrice;

  @Column(name = "model_price")
  private Long price;

  @Type(JsonType.class)
  @Column(columnDefinition = "JSON")
  private Map<String, ProductOptionCombined> modelCombined; // id: option_id, value

  @OneToOne(mappedBy = "model", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Inventory inventory;

  @JsonIgnore
  @Column(name = "model_is_published")
  private boolean isPublished = true;
}
