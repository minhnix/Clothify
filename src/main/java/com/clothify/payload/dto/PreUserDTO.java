package com.clothify.payload.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreUserDTO implements Serializable {
  @Serial private static final long serialVersionUID = 1L;
  private String email;
  private String password;
  private String token;
  private String status;
}
