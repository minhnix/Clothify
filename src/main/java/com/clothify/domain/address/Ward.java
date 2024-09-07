package com.clothify.domain.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ward")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Ward {
  @Id
  @GeneratedValue
  private UUID id;
  @Column
  private String name;
  @ManyToOne private District district;
}
