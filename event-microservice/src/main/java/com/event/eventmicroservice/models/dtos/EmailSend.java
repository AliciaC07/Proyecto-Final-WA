package com.event.eventmicroservice.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailSend {

    private String to;
    private String content;
    private String subject;
}
