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
@Table(name = "district")
public class District {
  @Id
  @GeneratedValue
  private UUID id;
  @Column
  private String name;
  @ManyToOne private City city;

  @OneToMany(mappedBy = "district", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Ward> wards;
}
