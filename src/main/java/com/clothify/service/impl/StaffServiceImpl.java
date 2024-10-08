package com.clothify.service.impl;

import com.clothify.common.CommonFunction;
import com.clothify.domain.enumuration.PermissionType;
import com.clothify.domain.enumuration.Role;
import com.clothify.domain.user.User;
import com.clothify.exception.BadRequestException;
import com.clothify.mapper.UserMapper;
import com.clothify.payload.request.staff.PermissionRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.payload.response.page.PageResponse;
import com.clothify.payload.response.user.UserResponse;
import com.clothify.repository.UserRepository;
import com.clothify.service.AuthService;
import com.clothify.service.StaffService;
import com.clothify.service.UserService;
import java.util.UUID;

import com.clothify.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
  private final UserService userService;
  private final AuthService authService;
  private final UserRepository userRepository;

  @Override
  public ApiResponse updatePermission(
      UUID adminId, UUID staffId, PermissionRequest permissionRequest) {
    User user = userService.getUser(staffId);
    if (Role.ROLE_ADMIN.toString().equals(permissionRequest.getPermission())) {
      throw new BadRequestException("You can not update permission for admin");
    }

    user.setPermission(PermissionType.valueOf(permissionRequest.getPermission()));
    userRepository.save(user);
    return ApiResponse.successWithoutMeta(
        UserMapper.INSTANCE.toResponse(user), "Update permission successfully");
  }

  @Override
  public ApiResponse deleteStaff(UUID adminId, UUID staffId) {
    authService.deleteAccount(staffId);
    return ApiResponse.successWithoutDataAndMeta("Delete staff successfully");
  }

  @Override
  public ApiResponse getStaffs(UUID adminId, Pageable pageable, String keyword) {
    Page<User> pages =
        userRepository
            .findAllByRoleAndDeletedAtIsNullAndFirstnameIsLikeIgnoreCaseOrLastnameIsLikeIgnoreCase(
                pageable, Role.ROLE_STAFF.toString(), keyword);
    PageResponse<User> response = new PageResponse<>(pages);

    return ApiResponse.successWithoutMeta(
        response.getContent().stream().map(UserMapper.INSTANCE::toResponse).toList(),
        "Get staffs successfully");
  }
}
