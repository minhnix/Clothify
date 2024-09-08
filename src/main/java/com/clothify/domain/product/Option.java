package com.clothify.domain.product;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Option extends AbstractAuditing {
  // option
  @Id
  @GeneratedValue
  private UUID id;

  private String name;
}
