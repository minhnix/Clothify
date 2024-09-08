package com.clothify.domain.order;

import com.clothify.domain.user.User;
import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.clothify.domain.product.Model;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "carts",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = {"user_id", "model_id"}),
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends AbstractAuditing {
  @Id
  @GeneratedValue
  @Column(name = "cart_id")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "model_id")
  private Model model;

  private Long quantity;
}
