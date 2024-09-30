package com.clothify.domain.product;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
  @GeneratedValue @Id private UUID id;
  private String name;

  @OneToMany(mappedBy = "brand")
  private List<Product> products = new ArrayList<>();
}
