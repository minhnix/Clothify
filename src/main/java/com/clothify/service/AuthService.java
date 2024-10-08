package com.clothify.service;

import com.clothify.domain.user.User;
import com.clothify.payload.request.auth.LoginRequest;
import com.clothify.payload.request.auth.SignUpRequest;
import com.clothify.payload.request.staff.AddStaffRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.security.jwt.Token;

import java.util.UUID;

public interface AuthService {
  ApiResponse registerCustomer(SignUpRequest user);

  Token login(LoginRequest loginRequest);

  Token doRefreshToken(String refreshToken);

  ApiResponse verifyEmail(String token);

  void requestDeleteAccount(UUID userId);

  void confirmDeleteAccount(UUID userId, String token);

  void deleteAccount(UUID userId);

  void addStaff(UUID userId, AddStaffRequest addStaffRequest);

  User registerStaff(String email, String password);
}
