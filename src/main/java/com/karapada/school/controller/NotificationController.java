package com.karapada.school.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.school.dto.NotificationRequest;
import com.karapada.school.service.EmailNotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private EmailNotificationService emailService;

    @PostMapping("/send")
    public void sendMail(@RequestBody NotificationRequest request) {
        emailService.send(request);
    }
}

