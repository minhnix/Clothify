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
@Table(name = "city")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class City {
  @Id
  @GeneratedValue
  private UUID id;
  @Column
  private String name;

  @OneToMany(mappedBy = "city", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<District> districts;
}
