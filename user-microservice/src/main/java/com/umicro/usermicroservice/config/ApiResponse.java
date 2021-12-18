package com.umicro.usermicroservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse {

    private HttpStatus status;
    private LocalDateTime date;
    private String message;

    public ApiResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        date = LocalDateTime.now();
    }
}