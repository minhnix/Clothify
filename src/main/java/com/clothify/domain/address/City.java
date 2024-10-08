package com.clothify.domain.address;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "city")
public class City {
  @Id
  @GeneratedValue
  private UUID id;
  @Column
  private String name;

  @OneToMany(mappedBy = "city", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<District> districts;
}
