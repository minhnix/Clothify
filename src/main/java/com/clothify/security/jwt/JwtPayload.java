package com.clothify.security.jwt;

import com.clothify.domain.enumuration.PermissionType;
import com.clothify.domain.enumuration.Role;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Role role;
    private PermissionType permission;
}
