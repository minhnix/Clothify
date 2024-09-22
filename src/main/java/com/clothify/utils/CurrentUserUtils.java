package com.clothify.utils;

import com.clothify.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtils {
  public static CustomUserDetails get() {
    return (CustomUserDetails)
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
