package com.clothify.repository;

import com.clothify.domain.user.Role;
import com.clothify.domain.enumuration.RoleName;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;

import java.util.Optional;

public interface RoleRepository extends EntityGraphJpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
