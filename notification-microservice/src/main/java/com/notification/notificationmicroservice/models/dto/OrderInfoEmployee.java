package com.notification.notificationmicroservice.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInfoEmployee {

    private String event;
    private String employee;
    private String email;
    private String client;

}
