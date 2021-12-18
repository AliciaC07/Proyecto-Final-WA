package com.umicro.usermicroservice.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "This field can't be null or blank.")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank(message = "This field can't be null or blank.")
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank(message = "This field can't be null or blank.")
    @Size(min = 3, max = 50)
    private String lastName;

    @NotBlank(message = "This field can't be null or blank.")
    @Size(min = 6, max = 50)
    private String password;

    @NotBlank(message = "This field can't be null or blank.")
    @Size(min = 3, max = 80)
    private String email;

    @NotBlank(message = "This field can't be null or blank.")
    private String role;

}
