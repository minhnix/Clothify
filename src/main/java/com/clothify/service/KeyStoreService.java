package com.clothify.service;

import com.clothify.domain.user.KeyStore;
import java.util.UUID;

public interface KeyStoreService {
  KeyStore createNewKeyStore(UUID userId, String refreshToken);

  KeyStore updateRefreshToken(UUID userId, String newRefreshToken, String oldRefreshToken);
}