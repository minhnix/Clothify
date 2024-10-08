package com.clothify.service.impl;

import com.clothify.domain.user.User;
import com.clothify.exception.NotFoundException;
import com.clothify.mapper.UserMapper;
import com.clothify.payload.request.user_detail.UserDetailRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.repository.UserRepository;
import com.clothify.service.AddressService;
import com.clothify.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final AddressService addressService;

  @Override
  public User getUser(UUID userId) {
    return userRepository
        .findById(userId)
        .orElseThrow(() -> new NotFoundException("User not found!"));
  }

  @Override
  public ApiResponse getUserDetail(UUID userId) {
    return ApiResponse.successWithoutMeta(
        UserMapper.INSTANCE.toResponse(getUser(userId)), "Get user detail successfully");
  }

  @Override
  public ApiResponse updateInfo(UUID userId, UserDetailRequest userDetailRequest) {
    User user = getUser(userId);
    user.setFirstname(userDetailRequest.getFirstname());
    user.setLastname(userDetailRequest.getLastname());
    user.setAvatar(userDetailRequest.getAvatar());
    user.setPhoneNumber(userDetailRequest.getPhoneNumber());
    user.setCity(addressService.getCity(userDetailRequest.getCityId()));
    user.setDistrict(addressService.getDistrict(userDetailRequest.getDistrictId()));
    user.setWard(addressService.getWard(userDetailRequest.getWardId()));
    user.setStreet(userDetailRequest.getStreet());
    userRepository.save(user);
    return ApiResponse.successWithoutMeta(
        UserMapper.INSTANCE.toResponse(user), "Update user info successfully");
  }
}
