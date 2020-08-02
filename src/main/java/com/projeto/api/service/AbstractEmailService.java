package com.projeto.api.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail() {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setFrom("51343b3e0-7495e5@inbox.mailtrap.io");
        sm.setTo(sender);
        sm.setSubject("Ola");
        sm.setText("Ola");
        javaMailSender.send(sm);
    }
}
