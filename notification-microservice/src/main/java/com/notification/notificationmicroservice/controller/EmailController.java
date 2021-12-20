package com.notification.notificationmicroservice.controller;

import com.notification.notificationmicroservice.models.EmailSend;
import com.notification.notificationmicroservice.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmailController {
    private final NotificationService notificationService;

    public EmailController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/user-notification")
    public ResponseEntity<String> sendEmail(@RequestBody EmailSend emailSend) {
        System.out.printf(emailSend.getContent());
        if (!notificationService.sendEmailNotification(emailSend)){
            return  ResponseEntity.ok("The email was not sent");
        }
        return ResponseEntity.ok("The email was send");

    }

}
