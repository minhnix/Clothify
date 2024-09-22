package com.clothify.service.impl;

import com.clothify.exception.BadRequestException;
import com.clothify.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class GmailService implements MailService {
  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String fromAddress;

  public GmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void sendMail(String to, String subject, String text) {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    try {
      helper.setFrom(fromAddress, "CLOTHIFY");
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(text, true);
    } catch (MessagingException | UnsupportedEncodingException e) {
      throw new BadRequestException(e.getMessage());
    }
    mailSender.send(message);
  }
}
