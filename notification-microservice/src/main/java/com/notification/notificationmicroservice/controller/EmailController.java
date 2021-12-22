package com.notification.notificationmicroservice.controller;

import com.notification.notificationmicroservice.models.EmailSend;
import com.notification.notificationmicroservice.models.dto.Order;
import com.notification.notificationmicroservice.models.dto.OrderInfoEmployee;
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
        if (!notificationService.sendEmailNotification(emailSend)){
            return  ResponseEntity.ok("The email was not sent");
        }
        return ResponseEntity.ok("The email was send");

    }

    @PostMapping("/order-notification")
    public ResponseEntity<String> sendEmailOrder(@RequestBody Order order) {
        if (!notificationService.sendEmailOrderNotification(order)){
            return  ResponseEntity.ok("The email was not sent");
        }
        return ResponseEntity.ok("The email was send");

    }

    @PostMapping("/employee-notification")
    public ResponseEntity<String> sendEmailOrderEmployee(@RequestBody OrderInfoEmployee order) {
        if (!notificationService.sendEmailOrderNotificationEmployee(order)){
            return  ResponseEntity.ok("The email was not sent");
        }
        return ResponseEntity.ok("The email was send");

    }

}
