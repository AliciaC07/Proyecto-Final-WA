package com.event.eventmicroservice.models.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Integer id;


    private String username;


    private String password;


    private String name;


    private String lastName;


    private String email;


    private RoleDTO role;


    private Boolean active = true;


}
