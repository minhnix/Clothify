package com.clothify.service;


import com.clothify.payload.request.LoginRequest;
import com.clothify.payload.request.SignUpRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.security.jwt.Token;

public interface AuthService {
    ApiResponse registerCustomer(SignUpRequest user);
    Token login(LoginRequest loginRequest);
    Token doRefreshToken(String refreshToken);
    ApiResponse verifyEmail(String token);
}

