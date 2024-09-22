package com.clothify.service.impl;

import static com.clothify.domain.constants.AppConstants.MAIL_VERIFY_EXPIRED_TIME_MINUTES;

import com.clothify.domain.enumuration.Role;
import com.clothify.domain.user.KeyStore;
import com.clothify.domain.user.User;
import com.clothify.event.RegisterCustomerEvent;
import com.clothify.exception.AuthFailureException;
import com.clothify.exception.BadRequestException;
import com.clothify.payload.dto.PreUserDTO;
import com.clothify.payload.request.LoginRequest;
import com.clothify.payload.request.SignUpRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.repository.KeyStoreRepository;
import com.clothify.repository.UserRepository;
import com.clothify.security.CustomUserDetails;
import com.clothify.security.jwt.JwtPayload;
import com.clothify.security.jwt.JwtUtils;
import com.clothify.security.jwt.Token;
import com.clothify.service.AuthService;
import com.clothify.service.KeyStoreService;
import com.clothify.utils.JsonUtils;
import com.clothify.utils.SecurityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final KeyStoreService keyStoreService;
  private final KeyStoreRepository keyStoreRepository;
  private final PasswordEncoder passwordEncoder;
  private final RedisTemplate<String, String> redisTemplate;
  private final UserRepository userRepository;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public ApiResponse registerCustomer(SignUpRequest user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new BadRequestException("Email already exists");
    }
    String encodedEmail = Base64.getEncoder().encodeToString(user.getEmail().getBytes());
    String token = SecurityUtils.genarateRandomString(32) + encodedEmail;
    PreUserDTO preUserDTO =
        PreUserDTO.builder()
            .email(user.getEmail())
            .status("PENDING")
            .token(token)
            .password(passwordEncoder.encode(user.getPassword()))
            .build();
    String json = JsonUtils.objectToJsonString(preUserDTO);
    redisTemplate
        .opsForValue()
        .set(preUserDTO.getEmail(), json, MAIL_VERIFY_EXPIRED_TIME_MINUTES, TimeUnit.MINUTES);
    applicationEventPublisher.publishEvent(new RegisterCustomerEvent(preUserDTO));
    return ApiResponse.successWithoutDataAndMeta("Please check your email to verify your account");
  }

  @Override
  @Transactional
  public Token login(LoginRequest loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
    User user = customUserDetails.getUser();
    JwtPayload payload =
        JwtPayload.builder()
            .userId(user.getId())
            .email(user.getEmail())
            .firstName(user.getFirstname())
            .lastname(user.getLastname())
            .status(user.getStatus())
            .role(user.getRole())
            .build();
    Token token = jwtUtils.generateTokenPair(payload);
    keyStoreService.updateRefreshToken(user.getId(), token.getRefreshToken(), null);
    return token;
  }

  @Override
  @Transactional
  public Token doRefreshToken(String refreshToken) {
    JwtPayload payload = jwtUtils.getPayLoadIfTokenValidated(refreshToken);
    UUID userId = payload.getUserId();
    KeyStore keyStore =
        keyStoreRepository
            .findByUserId(userId)
            .orElseThrow(() -> new AuthFailureException("Invalid user"));
    if (Objects.equals(keyStore.getRefreshToken(), refreshToken)) {
      Token token = jwtUtils.generateTokenPair(payload);
      keyStore.setRefreshToken(token.getRefreshToken());
      keyStoreRepository.save(keyStore);
      return token;
    } else throw new AuthFailureException("Invalid token");
  }

  @Override
  @Transactional
  public ApiResponse verifyEmail(String token) {
    String encodedEmail = token.substring(32);
    String email = new String(Base64.getDecoder().decode(encodedEmail));
    String json = redisTemplate.opsForValue().get(email);
    if (json == null) {
      throw new BadRequestException("Invalid token or token expired");
    }
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      PreUserDTO preUser = objectMapper.readValue(json, PreUserDTO.class);
      if (!token.equals(preUser.getToken())) {
        throw new BadRequestException("Invalid token");
      }
      User user = new User();
      user.setPassword(preUser.getPassword());
      user.setStatus("ACTIVE");
      user.setEmail(preUser.getEmail());
      user.setRole(Role.ROLE_CUSTOMER);
      User savedUser = userRepository.save(user);
      keyStoreService.createNewKeyStore(savedUser.getId(), null);

      return ApiResponse.successWithoutDataAndMeta("Account verified successfully");
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
