package com.event.eventmicroservice.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRecieveDTO {


    private String username;


    private String name;


    private String lastName;

    private String password;


    private String email;

    private String role;
}
