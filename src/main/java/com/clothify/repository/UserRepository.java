package com.clothify.repository;

import com.clothify.domain.enumuration.Role;
import com.clothify.domain.user.User;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository
    extends EntityGraphJpaRepository<User, UUID>, EntityGraphJpaSpecificationExecutor<User> {
  Optional<User> findByEmail(String email);

  Optional<User> findByEmail(String email, EntityGraph entityGraph);

  Boolean existsByEmail(String email);

  Optional<User> findByRole(Role role);

  @Query(
      nativeQuery = true,
      value =
          """
      SELECT * FROM users
      WHERE role = :role
      AND deleted_at IS NULL
      AND (firstname ILIKE CONCAT('%', :keyword, '%') OR lastname ILIKE CONCAT('%', :keyword, '%'))
""")
  Page<User> findAllByRoleAndDeletedAtIsNullAndFirstnameIsLikeIgnoreCaseOrLastnameIsLikeIgnoreCase(
      Pageable pageable, String role, String keyword);
}
