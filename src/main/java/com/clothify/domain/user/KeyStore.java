package com.clothify.domain.user;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.clothify.domain.enumuration.AuthProvider;
import jakarta.persistence.*;
import java.sql.Timestamp;
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
@Table(name = "token_store")
public class KeyStore extends AbstractAuditing {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "user_id", insertable = false, updatable = false)
  private UUID userId;

  @Column(name = "refresh_token", length = 1024)
  private String refreshToken;

  @Column(name = "revoked_at")
  private Timestamp revokedAt;

  @Enumerated(EnumType.STRING)
  private AuthProvider provider;

  private String deviceInfo;
  private String ipAddress;
}
