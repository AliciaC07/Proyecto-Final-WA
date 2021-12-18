package com.umicro.usermicroservice.security;

import com.umicro.usermicroservice.models.User;
import com.umicro.usermicroservice.repository.UserRepository;
import com.umicro.usermicroservice.security.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;

@Service
public class MyUserDetailsService  implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsernameAndActiveTrue(s)
                .orElseThrow(() -> new EntityNotFoundException("User was not found."));
        return new MyUserDetails(user);
    }
}
