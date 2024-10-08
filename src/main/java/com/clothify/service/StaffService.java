package com.clothify.service;

import com.clothify.payload.request.staff.PermissionRequest;
import com.clothify.payload.response.ApiResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface StaffService {
  ApiResponse updatePermission(UUID adminId, UUID staffId, PermissionRequest permissionRequest);

  ApiResponse deleteStaff(UUID adminId, UUID staffId);

  ApiResponse getStaffs(UUID adminId, Pageable pageable, String keyword);

}
