package com.umicro.usermicroservice.controllers;

import com.umicro.usermicroservice.models.User;
import com.umicro.usermicroservice.models.dtos.UserDTO;
import com.umicro.usermicroservice.models.dtos.UserRecieveDTO;
import com.umicro.usermicroservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/user/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('Admin')")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/user/{username}")
    public User findUserByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }
    @GetMapping("/user-id/{id}")
    @PreAuthorize("isAuthenticated()")
    public User findUserById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody UserDTO user, @PathVariable Integer id){
        User old = userService.findById(id);
        return userService.updateUser(old, user);

    }

    @DeleteMapping("/user/{username}")
    @PreAuthorize("hasAuthority('Admin')")
    @ResponseStatus(HttpStatus.OK)
    public User deleteUser(@PathVariable String username){
        return userService.deleteUserByUsername(username);
    }

    @GetMapping("/users-employee")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<UserRecieveDTO> findAllUserEmployee(){
        return userService.findUserByRol();
    }



}