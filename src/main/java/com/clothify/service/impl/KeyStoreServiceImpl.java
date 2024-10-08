package com.clothify.service.impl;

import com.clothify.common.CommonFunction;
import com.clothify.constant.MessageConstant;
import com.clothify.domain.user.KeyStore;
import com.clothify.domain.user.User;
import com.clothify.exception.NotFoundException;
import com.clothify.repository.KeyStoreRepository;
import com.clothify.repository.UserRepository;
import com.clothify.service.KeyStoreService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyStoreServiceImpl implements KeyStoreService {
  private final KeyStoreRepository keyStoreRepository;
  private final UserRepository userRepository;

  @Override
  public KeyStore createNewKeyStore(UUID userId, String refreshToken) {
    KeyStore keyStore = new KeyStore();
    User user = userRepository.getReferenceById(userId);
    keyStore.setUser(user);
    keyStore.setRefreshToken(refreshToken);
    return keyStoreRepository.save(keyStore);
  }

  @Override
  public KeyStore updateRefreshToken(UUID userId, String newRefreshToken, String oldRefreshToken) {
    KeyStore keyStore =
        keyStoreRepository
            .findByUserId(userId)
            .orElseThrow(() -> new NotFoundException("Not found key store"));
    keyStore.setRefreshToken(newRefreshToken);
    return keyStoreRepository.save(keyStore);
  }

}
