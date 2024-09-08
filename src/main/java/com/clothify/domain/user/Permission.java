package com.clothify.domain.user;

import com.clothify.domain.abstract_entity.AbstractAuditing;

import java.util.UUID;

import com.clothify.domain.enumuration.PermissionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends AbstractAuditing {
  @Id @GeneratedValue private UUID id;

  @Enumerated(EnumType.STRING)
  private PermissionType permission;
}
