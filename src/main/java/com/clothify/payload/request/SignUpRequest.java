package com.clothify.payload.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpRequest {
    private String email;
    private String password;

}
