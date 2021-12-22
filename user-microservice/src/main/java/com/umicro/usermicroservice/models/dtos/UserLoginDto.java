package com.umicro.usermicroservice.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {

    private Integer id;

    private String name;

    private String lastname;

    private String username;

    private String email;

    private RoleDto role;

    private String token;

    private String type = "Bearer";
}
