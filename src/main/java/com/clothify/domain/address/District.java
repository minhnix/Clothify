package com.clothify.domain.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "district")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
