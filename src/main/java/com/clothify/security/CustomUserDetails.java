package com.clothify.security;

import com.clothify.domain.enumuration.PermissionType;
import com.clothify.domain.enumuration.Role;
import com.clothify.domain.user.User;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
  public final Collection<? extends GrantedAuthority> authorities;
  private final User user;

  public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
    this.user = user;
    this.authorities = authorities;
  }

  public static CustomUserDetails create(User user) {
    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
    return new CustomUserDetails(user, authorities);
  }

  public PermissionType getPermissionType() {
    return user.getPermission();
  }

  public boolean isAdmin() {
    return user.getRole().equals(Role.ROLE_ADMIN);
  }

  public UUID getId() {
    return user.getId();
  }

  public User getUser() {
    return user;
  }

  public Role getRole() {
    return user.getRole();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomUserDetails that = (CustomUserDetails) o;
    return Objects.equals(user.getId(), that.user.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(user.getId());
  }
}
