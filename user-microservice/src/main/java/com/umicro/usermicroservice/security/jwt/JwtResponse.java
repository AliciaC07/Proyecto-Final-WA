package com.umicro.usermicroservice.security.jwt;

import com.umicro.usermicroservice.models.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {

    private Integer id;

    private String token;

    private String username;

    private String name;

    private String lastName;

    private String email;

    private Role role;

    private String type = "Bearer";
}
