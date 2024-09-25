package com.clothify.payload.response.store;

import com.clothify.domain.Store;
import com.clothify.payload.response.address.AddressResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreResponse {
  private UUID id;
  private String phoneNumber;
  private String name;
  private String logo;
  private String description;
  private List<String> imagesDescription;
  private List<String> socialUrls;
  private AddressResponse city;
  private AddressResponse district;
  private AddressResponse ward;
  private String street;
  private Timestamp openTime;
  private Timestamp closeTime;

  public static StoreResponse storeResponse(Store store) {
    if (store == null) return null;
    return StoreResponse.builder()
        .id(store.getId())
        .phoneNumber(store.getPhoneNumber())
        .name(store.getName())
        .logo(store.getLogo())
        .description(store.getDescription())
        .imagesDescription(store.getBranchImagesDescription())
        .socialUrls(store.getSocialUrls())
        .city(AddressResponse.toAddressResponse(store.getCity()))
        .district(AddressResponse.toAddressResponse(store.getDistrict()))
        .ward(AddressResponse.toAddressResponse(store.getWard()))
        .street(store.getStreet())
        .openTime(store.getOpenTime())
        .closeTime(store.getCloseTime())
        .build();
  }
}
