package com.clothify.domain.user;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.clothify.domain.address.City;
import com.clothify.domain.address.District;
import com.clothify.domain.address.Ward;
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
  @Email @NotNull private String email;
   private String emailRestore;
  @JsonIgnore private String password;
  private String status;
  private String firstname;
  private String lastname;
  private String avatar;

  @Enumerated(EnumType.STRING)
  private Role role;

  private String phoneNumber;

  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  @ManyToOne
  @JoinColumn(name = "district_id")
  private District district;

  @ManyToOne
  @JoinColumn(name = "ward_id")
  private Ward ward;

  private String street;

  @Enumerated(EnumType.STRING)
  private PermissionType permission;

  private String deleteToken;
}
