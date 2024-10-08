package com.clothify.payload.response.user;

import com.clothify.payload.response.address.AddressResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
  private UUID id;
  private String firstname;
  private String lastname;
  private String avatar;
  private String phoneNumber;
  private AddressResponse city;
  private AddressResponse district;
  private AddressResponse ward;
  private String street;
}
