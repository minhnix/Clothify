package com.clothify.payload.request.user_detail;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserDetailRequest {
  private String firstname;
  private String lastname;
  private String avatar;
  private String phoneNumber;
  private UUID cityId;
  private UUID districtId;
  private UUID wardId;
  private String street;
}
