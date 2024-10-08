package com.clothify.service;

import com.clothify.domain.Store;
import com.clothify.payload.request.store.StoreRequest;
import com.clothify.payload.response.ApiResponse;

import java.util.UUID;

public interface StoreService {

  ApiResponse updateStore(UUID userId, StoreRequest storeRequest);

  ApiResponse getStoreInfo();

  Store getStore();

  void createStore();
}
