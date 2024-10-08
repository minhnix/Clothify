package com.clothify.payload.request.store;


import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRequest {
  private String phoneNumber;
  private String name;
  private String logo;
  private String description;
  private List<String> imagesDescription;
  private List<String> socialUrls;
  private UUID cityId;
  private UUID districtId;
  private UUID wardId;
  private String street;
  private Timestamp openTime;
  private Timestamp closeTime;
}
