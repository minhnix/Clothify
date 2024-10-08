package com.clothify.service.impl;

import com.clothify.domain.Store;
import com.clothify.domain.enumuration.Role;
import com.clothify.domain.user.User;
import com.clothify.exception.ForbiddenException;
import com.clothify.exception.NotFoundException;
import com.clothify.payload.request.store.StoreRequest;
import com.clothify.payload.response.ApiResponse;
import com.clothify.payload.response.store.StoreResponse;
import com.clothify.repository.StoreRepository;
import com.clothify.repository.UserRepository;
import com.clothify.service.AddressService;
import com.clothify.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
  private final StoreRepository storeRepository;
  private final UserRepository userRepository;
  private final AddressService addressService;

  @Override
  public ApiResponse updateStore(UUID userId, StoreRequest storeRequest) {
    Store store = getStore();
    if (!store.getUser().getId().equals(userId)) {
      throw new ForbiddenException("You are not allowed to update store info");
    }

    store.setPhoneNumber(storeRequest.getPhoneNumber());
    store.setName(storeRequest.getName());
    store.setDescription(storeRequest.getDescription());
    store.setLogo(storeRequest.getLogo());
    store.setDescription(storeRequest.getDescription());
    store.setBranchImagesDescription(storeRequest.getImagesDescription());
    store.setSocialUrls(storeRequest.getSocialUrls());
    store.setCity(addressService.getCity(storeRequest.getCityId()));
    store.setDistrict(addressService.getDistrict(storeRequest.getDistrictId()));
    store.setWard(addressService.getWard(storeRequest.getWardId()));
    store.setStreet(storeRequest.getStreet());
    store.setOpenTime(storeRequest.getOpenTime());
    store.setCloseTime(storeRequest.getCloseTime());

    store = storeRepository.save(store);
    return ApiResponse.successWithoutMeta(
        StoreResponse.storeResponse(store), "Update store info successfully");
  }

  @Override
  public ApiResponse getStoreInfo() {
    Store store = getStore();
    return ApiResponse.successWithoutMeta(
        StoreResponse.storeResponse(store), "Get store info successfully");
  }

  @Override
  public Store getStore() {
    return storeRepository.findAll().stream()
        .findFirst()
        .orElseThrow(() -> new NotFoundException("Store not found"));
  }

  @Override
  public void createStore() {
    User user = userRepository.findByRole(Role.ROLE_ADMIN).orElse(null);
    if (user == null) {
      return;
    }
    if (storeRepository.count() == 0) {
      Store store = new Store();
      store.setUser(user);
      storeRepository.save(store);
    }
  }
}
