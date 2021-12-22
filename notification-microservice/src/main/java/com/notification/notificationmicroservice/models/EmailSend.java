package com.notification.notificationmicroservice.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailSend {

    private String to;
    private String content;
    private String subject;
    private String userName;
    private String name;

}
