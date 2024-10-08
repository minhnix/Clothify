package com.clothify.repository;

import com.clothify.domain.user.KeyStore;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyStoreRepository extends JpaRepository<KeyStore, UUID> {
    Optional<KeyStore> findByUserId(UUID userId);
}
