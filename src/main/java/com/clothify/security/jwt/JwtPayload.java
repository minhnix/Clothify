package com.clothify.security.jwt;

import com.clothify.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtPayload {

    private UUID userId;
    private String email;
    private String firstName;
    private String lastname;
    private String status;
    private Set<Role> roles;
}
