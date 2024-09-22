package com.clothify.domain.user;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.clothify.domain.enumuration.PermissionType;
import com.clothify.domain.enumuration.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractAuditing {
  @Id @GeneratedValue private UUID id;
  @Email @NotNull @NaturalId private String email;
  @JsonIgnore private String password;
  private String status;
  private String firstname;
  private String lastname;
  private String avatar;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Enumerated(EnumType.STRING)
  private PermissionType permission;
}
