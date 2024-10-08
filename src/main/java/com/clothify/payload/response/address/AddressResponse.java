package com.clothify.payload.response.address;

import com.clothify.domain.address.City;
import com.clothify.domain.address.District;
import com.clothify.domain.address.Ward;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddressResponse {
  private UUID id;
  private String name;

  public static AddressResponse toResponse(City city) {
    if (city == null) return null;
    return AddressResponse.builder().id(city.getId()).name(city.getName()).build();
  }

  public static AddressResponse toResponse(District district) {
    if (district == null) return null;
    return AddressResponse.builder().id(district.getId()).name(district.getName()).build();
  }

  public static AddressResponse toResponse(Ward ward) {
    if (ward == null) return null;
    return AddressResponse.builder().id(ward.getId()).name(ward.getName()).build();
  }

  public static AddressResponse toResponse(String id, String name) {
    if (id == null || name == null) return null;
    return AddressResponse.builder().id(UUID.fromString(id)).name(name).build();
  }

  public static AddressResponse toResponse(UUID id, String name) {
    if (id == null || name == null) return null;
    return AddressResponse.builder().id(id).name(name).build();
  }
}
