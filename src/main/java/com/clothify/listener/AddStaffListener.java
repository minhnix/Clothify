package com.clothify.listener;

import com.clothify.domain.constants.AppConstants;
import com.clothify.event.AddStaffEvent;
import com.clothify.event.RegisterCustomerEvent;
import com.clothify.payload.dto.PreUserDTO;
import com.clothify.service.MailService;
import com.clothify.utils.TemplateUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddStaffListener {
  private final MailService mailService;

  @Async
  @EventListener
  public void sendMailAddStaff(AddStaffEvent event) {
    mailService.sendMail(
        event.user().getEmail(),
        "Invite to join Clothify Store",
        TemplateUtils.getMailInviteToStore(
            event.user().getEmail(), event.user().getPassword(), "link")); // todo : using env
  }
}
