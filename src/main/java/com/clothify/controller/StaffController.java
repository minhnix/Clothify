package com.clothify.controller;

import com.clothify.annotation.CurrentUser;
import com.clothify.domain.constants.PageConstants;
import com.clothify.exception.AuthFailureException;
import com.clothify.payload.request.auth.LoginRequest;
import com.clothify.payload.request.auth.SignUpRequest;
import com.clothify.payload.request.staff.AddStaffRequest;
import com.clothify.payload.request.staff.PermissionRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.security.CustomUserDetails;
import com.clothify.security.jwt.Token;
import com.clothify.service.AuthService;
import com.clothify.service.StaffService;
import com.clothify.utils.PageableUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/admin/staffs")
@Slf4j
@RequiredArgsConstructor
public class StaffController {
  private final AuthService authService;
  private final StaffService staffService;

  @PostMapping()
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ApiResponse> addStaff(
      @CurrentUser CustomUserDetails customUserDetails,
      @Valid @RequestBody AddStaffRequest addStaffRequest) {
    authService.addStaff(customUserDetails.getUser().getId(), addStaffRequest);
    return ResponseEntity.ok(
        new ApiResponse(null, "Staff added successfully", HttpStatus.OK.value()));
  }

  @PatchMapping("/{staffId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ApiResponse> updateStaff(
      @CurrentUser CustomUserDetails customUserDetails,
      @PathVariable UUID staffId,
      @RequestBody PermissionRequest permissionRequest) {
    return ResponseEntity.ok(
        staffService.updatePermission(
            customUserDetails.getUser().getId(), staffId, permissionRequest));
  }

  @DeleteMapping("/{staffId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ApiResponse> deleteStaff(
      @CurrentUser CustomUserDetails customUserDetails, @PathVariable UUID staffId) {
    return ResponseEntity.ok(
        staffService.deleteStaff(customUserDetails.getUser().getId(), staffId));
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ApiResponse getStaffs(
      @CurrentUser CustomUserDetails customUserDetails,
      @RequestParam(value = "page", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = PageConstants.DEFAULT_PAGE_SIZE) int size,
      @RequestParam(value = "keyword", defaultValue = "") String keyword) {
    Pageable pageable = PageableUtils.getPageable(page, size);
    return staffService.getStaffs(customUserDetails.getUser().getId(), pageable, keyword);
  }
}
