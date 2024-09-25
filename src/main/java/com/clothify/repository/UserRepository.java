package com.clothify.repository;

import com.clothify.domain.enumuration.Role;
import com.clothify.domain.user.User;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository
    extends EntityGraphJpaRepository<User, UUID>, EntityGraphJpaSpecificationExecutor<User> {
  Optional<User> findByEmail(String email);

  Optional<User> findByEmail(String email, EntityGraph entityGraph);

  Boolean existsByEmail(String email);

  Optional<User> findByRole(Role role);
}
