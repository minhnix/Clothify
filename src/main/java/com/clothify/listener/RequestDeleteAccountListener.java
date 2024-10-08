package com.clothify.listener;

import com.clothify.event.AddStaffEvent;
import com.clothify.event.RequestDeleteAccountEvent;
import com.clothify.service.MailService;
import com.clothify.utils.TemplateUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RequestDeleteAccountListener {
  private final MailService mailService;

  @Async
  @EventListener
  public void sendMailAddStaff(RequestDeleteAccountEvent event) {
    mailService.sendMail(
        event.user().getEmail(),
        "Delete Account",
        TemplateUtils.getMailRequestDeleteAccount(
            event.user().getEmail(), event.user().getToken()));
  }
}
