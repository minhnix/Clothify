package com.clothify.controller;

import com.clothify.exception.AuthFailureException;
import com.clothify.payload.request.auth.LoginRequest;
import com.clothify.payload.request.auth.SignUpRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.security.jwt.JwtUtils;
import com.clothify.security.jwt.Token;
import com.clothify.service.AuthService;
import com.clothify.service.KeyStoreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  private final KeyStoreService keyStoreService;
  private final JwtUtils jwtUtils;

  @PostMapping("/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse registerUser(@Valid @RequestBody SignUpRequest request) {
    return authService.registerCustomer(request);
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
    try {
      Token token = authService.login(loginRequest);
      return ResponseEntity.ok(new ApiResponse(token, "login successfully", HttpStatus.OK.value()));
    } catch (AuthenticationException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(
              new ApiResponse(
                  null, "Username or password incorrect", HttpStatus.UNAUTHORIZED.value()));
    }
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<ApiResponse> doRefreshToken(HttpServletRequest request) {
    String refreshToken = request.getHeader("refresh-token");
    if (!StringUtils.hasText(refreshToken)) throw new AuthFailureException("Invalid token");
    Token token = authService.doRefreshToken(refreshToken);
    return ResponseEntity.ok(new ApiResponse(token, "Get new access token", HttpStatus.OK.value()));
  }

  @GetMapping("/verify-email")
  public ResponseEntity<ApiResponse> verifyEmail(@RequestParam("token") String token) {
    return ResponseEntity.ok(authService.verifyEmail(token));
  }
}
