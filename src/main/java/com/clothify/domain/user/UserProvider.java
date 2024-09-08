package com.clothify.domain.user;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.clothify.domain.enumuration.AuthProvider;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_provider")
public class UserProvider extends AbstractAuditing {

  @Id @GeneratedValue private UUID id;

  @Enumerated(EnumType.STRING)
  private AuthProvider provider;

  private String providerId;

  private String email;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
