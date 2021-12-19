package com.event.eventmicroservice.config;

import com.event.eventmicroservice.models.dtos.UserDTO;
import com.event.eventmicroservice.services.EventService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final EventService eventService;

    public MyUserDetailsService(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO userDTO = eventService.retrieveClient(s);
        return new MyUserDetails(userDTO);
    }
}
