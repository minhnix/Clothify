package com.clothify.service;

public interface MailService {
    void sendMail(String to, String subject, String text);
}
