package com.clothify.payload.request.staff;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddStaffRequest {
  private List<String> emails;
}
