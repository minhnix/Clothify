package com.clothify.domain;

import com.clothify.domain.enumuration.AuthProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token_store")
public class TokenStore extends AbstractAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private UUID userId;

    @Column(name = "refresh_token")
    private UUID refreshToken;

    @Column(name = "revoked_at")
    private Timestamp revokedAt;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String deviceInfo;
    private String ipAddress;
}