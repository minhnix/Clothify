package com.clothify.domain.address;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ward")
public class Ward {
  @Id
  @GeneratedValue
  private UUID id;
  @Column
  private String name;
  @ManyToOne private District district;
}
