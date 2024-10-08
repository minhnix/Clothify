package com.clothify.service;

import com.clothify.domain.user.User;
import com.clothify.payload.request.staff.PermissionRequest;
import com.clothify.payload.request.user_detail.UserDetailRequest;
import com.clothify.payload.response.ApiResponse;
import java.util.UUID;

public interface UserService {
  User getUser(UUID userId);

  ApiResponse getUserDetail(UUID userId);

  ApiResponse updateInfo(UUID userId, UserDetailRequest userDetailRequest);
}
