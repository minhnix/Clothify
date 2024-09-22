package com.clothify.domain.product;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractAuditing {
  @Id
  @GeneratedValue
  @Column(name = "category_id")
  private UUID id;

  @Column(name = "category_name", unique = true)
  private String name;
}
