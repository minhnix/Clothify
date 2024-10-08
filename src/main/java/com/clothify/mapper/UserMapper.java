package com.clothify.mapper;

import com.clothify.domain.address.City;
import com.clothify.domain.address.District;
import com.clothify.domain.address.Ward;
import com.clothify.domain.user.User;
import com.clothify.payload.response.address.AddressResponse;
import com.clothify.payload.response.user.UserResponse;
import com.clothify.security.jwt.JwtPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "id", source = "source.userId")
  User toUser(JwtPayload source);

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "firstname", source = "source.firstname")
  @Mapping(target = "lastname", source = "source.lastname")
  @Mapping(target = "avatar", source = "source.avatar")
  @Mapping(target = "phoneNumber", source = "source.phoneNumber")
  @Mapping(
      target = "city",
      expression = "java(toCityResponse(source.getCity()))")
  @Mapping(
      target = "district",
      expression =
          "java(toDistrictResponse(source.getDistrict()))")
  @Mapping(
      target = "ward",
      expression = "java(toWardResponse(source.getWard()))")
  @Mapping(target = "street", source = "source.street")
  UserResponse toResponse(User source);

  default AddressResponse toCityResponse(City city) {
    return AddressResponse.toResponse(city);
  }

  default AddressResponse toDistrictResponse(District district) {
    return AddressResponse.toResponse(district);
  }

  default AddressResponse toWardResponse(Ward ward) {
    return AddressResponse.toResponse(ward);
  }
}
