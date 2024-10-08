package com.clothify.controller;

import com.clothify.annotation.CurrentUser;
import com.clothify.payload.request.user_detail.UserDetailRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.security.CustomUserDetails;
import com.clothify.service.AuthService;
import com.clothify.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final AuthService authService;

  @GetMapping
  public ApiResponse getMyInfo(@CurrentUser CustomUserDetails customUserDetails) {
    return userService.getUserDetail(customUserDetails.getUser().getId());
  }

  @PatchMapping
  public ApiResponse updateInfo(
      @CurrentUser CustomUserDetails customUserDetails,
      @RequestBody UserDetailRequest userDetailRequest) {
    return userService.updateInfo(customUserDetails.getUser().getId(), userDetailRequest);
  }

  @PostMapping("/request-delete-account")
  public ResponseEntity<ApiResponse> requestDeleteAccount(
      @CurrentUser CustomUserDetails customUserDetails) {
    authService.requestDeleteAccount(customUserDetails.getUser().getId());
    return ResponseEntity.ok(
        new ApiResponse(null, "Request delete account successfully", HttpStatus.OK.value()));
  }

  @PostMapping("/confirm-delete-account")
  public ResponseEntity<ApiResponse> confirmDeleteAccount(
      @CurrentUser CustomUserDetails customUserDetails,
      @RequestParam(value = "token") String token) {
    authService.confirmDeleteAccount(customUserDetails.getUser().getId(), token);
    return ResponseEntity.ok(
        new ApiResponse(null, "Delete account successfully", HttpStatus.OK.value()));
  }
}
