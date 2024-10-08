package com.clothify.service.impl;

import static com.clothify.domain.constants.AppConstants.MAIL_VERIFY_EXPIRED_TIME_MINUTES;

import com.clothify.common.CommonFunction;
import com.clothify.domain.constants.AppConstants;
import com.clothify.domain.enumuration.Role;
import com.clothify.domain.user.KeyStore;
import com.clothify.domain.user.User;
import com.clothify.event.AddStaffEvent;
import com.clothify.event.RegisterCustomerEvent;
import com.clothify.event.RequestDeleteAccountEvent;
import com.clothify.exception.AuthFailureException;
import com.clothify.exception.BadRequestException;
import com.clothify.payload.dto.PreUserDTO;
import com.clothify.payload.request.auth.LoginRequest;
import com.clothify.payload.request.auth.SignUpRequest;
import com.clothify.payload.request.staff.AddStaffRequest;
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
    if (Boolean.TRUE.equals(userRepository.existsByEmail(user.getEmail()))) {
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

  @Override
  public void requestDeleteAccount(UUID userId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new BadRequestException("User not found"));
    if (user.getStatus().equals("DELETED")) {
      throw new BadRequestException("Account already deleted");
    }

    String deleteToken = CommonFunction.generateCode(8);
    user.setDeleteToken(deleteToken);
    userRepository.save(user);

    PreUserDTO preUserDTO = PreUserDTO.builder().email(user.getEmail()).token(deleteToken).build();

    applicationEventPublisher.publishEvent(new RequestDeleteAccountEvent(preUserDTO));
  }

  @Override
  public void confirmDeleteAccount(UUID userId, String token) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new BadRequestException("User not found"));
    if (!token.equals(user.getDeleteToken())) {
      throw new BadRequestException("Invalid token");
    }
    user.setEmailRestore(user.getEmail());
    user.setEmail(CommonFunction.generateRandomEmail(10));
    user.setDeletedAt(CommonFunction.getCurrentDateTime());
    userRepository.save(user);
  }

  @Override
  public void deleteAccount(UUID userId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new BadRequestException("User not found"));

    if (Role.ROLE_ADMIN.equals(user.getRole())) {
      throw new BadRequestException("You can not delete admin");
    }

    user.setEmailRestore(user.getEmail());
    user.setEmail(CommonFunction.generateRandomEmail(10));
    user.setDeletedAt(CommonFunction.getCurrentDateTime());
    userRepository.save(user);
  }

  @Override
  public void addStaff(UUID userId, AddStaffRequest addStaffRequest) {
    for (String staffEmail : addStaffRequest.getEmails()) {
      if (Boolean.TRUE.equals(userRepository.existsByEmail(staffEmail))) {
        throw new BadRequestException("Email already exists");
      }

      PreUserDTO preUserDTO =
          PreUserDTO.builder()
              .email(staffEmail)
              .password(CommonFunction.generatePassword())
              .build();

      registerStaff(preUserDTO.getEmail(), preUserDTO.getPassword());
      //      String json = JsonUtils.objectToJsonString(preUserDTO);
      //      redisTemplate
      //          .opsForValue()
      //          .set(preUserDTO.getEmail(), json, AppConstants.MAIL_ADD_STAFF, TimeUnit.MINUTES);
      applicationEventPublisher.publishEvent(new AddStaffEvent(preUserDTO));
    }
  }

  @Override
  public User registerStaff(String email, String password) {
    User user = new User();
    user.setPassword(passwordEncoder.encode(password));
    user.setFirstname("Staff");
    user.setStatus("ACTIVE");
    user.setEmail(email);
    user.setRole(Role.ROLE_STAFF);
    User savedUser = userRepository.save(user);
    keyStoreService.createNewKeyStore(savedUser.getId(), null);
    return savedUser;
  }
}
