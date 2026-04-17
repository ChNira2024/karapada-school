package com.karapada.school.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.karapada.school.dto.NotificationRequest;

@Service
public class EmailNotificationService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(NotificationRequest request) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(request.getTo());
        mail.setSubject(request.getSubject());
        mail.setText(request.getMessage());

        mailSender.send(mail);
    }
}
