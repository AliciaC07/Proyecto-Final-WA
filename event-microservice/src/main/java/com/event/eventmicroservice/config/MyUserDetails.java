package com.event.eventmicroservice.config;

import com.event.eventmicroservice.models.dtos.RoleDTO;
import com.event.eventmicroservice.models.dtos.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private final UserDTO userDTO;

    public MyUserDetails(UserDTO userDTO) {
        this.userDTO = userDTO;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<RoleDTO> roles = new ArrayList<>();
        roles.add(userDTO.getRole());
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public UserDTO getUser() {
        return userDTO;
    }

    public Integer getId() {
        return userDTO.getId();
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userDTO.getActive();
    }
}
