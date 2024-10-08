package com.clothify.controller;

import com.clothify.annotation.CurrentUser;
import com.clothify.payload.request.store.StoreRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.security.CustomUserDetails;
import com.clothify.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/store")
@RequiredArgsConstructor
public class StoreController {
  private final StoreService storeService;

  @GetMapping
  public ResponseEntity<ApiResponse> getStoreInfo() {
    return ResponseEntity.ok(storeService.getStoreInfo());
  }

  @PatchMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ApiResponse> updateStoreInfo(
          @CurrentUser CustomUserDetails customUserDetails,
          @Valid @RequestBody StoreRequest storeRequest
          ) {
    return ResponseEntity.ok(storeService.updateStore(customUserDetails.getUser().getId(), storeRequest));
  }
}
