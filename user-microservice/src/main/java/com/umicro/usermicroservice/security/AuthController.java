package com.umicro.usermicroservice.security;


import com.umicro.usermicroservice.config.ApiResponse;
import com.umicro.usermicroservice.models.Role;
import com.umicro.usermicroservice.models.User;
import com.umicro.usermicroservice.models.dtos.EmailSend;
import com.umicro.usermicroservice.models.dtos.LoginRequest;
import com.umicro.usermicroservice.models.dtos.SignUpRequest;
import com.umicro.usermicroservice.models.dtos.UserLoginDto;
import com.umicro.usermicroservice.security.jwt.JwtUtil;
import com.umicro.usermicroservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;



    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JwtUtil jwtUtil,
                          ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;

    }

    @PostMapping("/auth/signup")
    public ApiResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        User user = modelMapper.map(signUpRequest, User.class);
        Role role = new Role();
        role.setName(signUpRequest.getRole());
        user.setRole(role);
        userService.save(user);
        EmailSend emailSend = new EmailSend();
        emailSend.setTo(user.getEmail());
        emailSend.setUserName(user.getUsername());
        emailSend.setName(user.getName()+" "+user.getLastName());
        emailSend.setSubject("Welcome to photo factory");
        String response = userService.notificationSender(emailSend);
        System.out.print(response);
        return new ApiResponse(HttpStatus.OK, "User registered sucessfully.");
    }

    @PostMapping("/auth/login")
    public UserLoginDto doLogin(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtil.generateJwtToken(auth);
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        UserLoginDto login = modelMapper.map(userDetails.getUser(), UserLoginDto.class);
        login.setLastname(userDetails.getUser().getLastName());
        login.setToken(jwt);
        return login;
    }
    @GetMapping("/auth/user/{username}")
    public User findUserByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

}
