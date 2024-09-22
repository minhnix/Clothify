package com.clothify.listener;

import com.clothify.domain.constants.AppConstants;
import com.clothify.event.RegisterCustomerEvent;
import com.clothify.service.MailService;
import com.clothify.utils.TemplateUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisterCustomerListener {
  private final MailService mailService;

  @Async
  @EventListener
  public void sendMail(RegisterCustomerEvent event) {
    mailService.sendMail(
        event.user().getEmail(),
        "Verify your email",
        TemplateUtils.getMailRegisterTemplate(
            event.user().getEmail(),
            AppConstants.MAIL_VERIFY_EXPIRED_TIME_MINUTES + " phút",
            "http://localhost:8080/api/v1/auth/verify-email?token=" + event.user().getToken())); // todo : using env
  }
}
