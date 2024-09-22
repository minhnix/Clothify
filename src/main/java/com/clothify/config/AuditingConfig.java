package com.clothify.config;

import com.clothify.domain.constants.AppConstants;
import com.clothify.security.CustomUserDetails;
import java.util.Optional;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {
  @Bean
  public AuditorAware<UUID> auditorProvider() {
    return new SpringSecurityAuditAware();
  }
}

class SpringSecurityAuditAware implements AuditorAware<UUID> {

  @Override
  public Optional<UUID> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null
        || !authentication.isAuthenticated()
        || authentication instanceof AnonymousAuthenticationToken) {
      return Optional.of(AppConstants.SYSTEM);
    }

    CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

    return Optional.ofNullable(userPrincipal.getId());
  }
}
