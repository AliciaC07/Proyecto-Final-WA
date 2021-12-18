package com.umicro.usermicroservice.service;

import com.umicro.usermicroservice.models.Role;
import com.umicro.usermicroservice.models.User;
import com.umicro.usermicroservice.models.dtos.UserDTO;
import com.umicro.usermicroservice.repository.RoleRepository;
import com.umicro.usermicroservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public User save(User user){
        Role role = roleRepository.findByNameAndActiveTrue(user.getRole().getName())
                .orElseThrow(()-> new EntityNotFoundException("This role was not found"));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Iterable<User> findAll(){
        return userRepository.findAllByActiveTrue();
    }

    public User findByUsername(String username){
        return userRepository.findUserByUsernameAndActiveTrue(username)
                .orElseThrow(()-> new EntityNotFoundException("This user was not found"));
    }

    public User findById(Integer id){
        return userRepository.findUserByIdAndActiveTrue(id)
                .orElseThrow(()-> new EntityNotFoundException("This user was not found"));
    }

    @Transactional
    public User updateUser(User old, UserDTO user){
        old.setName(user.getName());
        old.setLastName(user.getLastName());
        Role role = roleRepository.findByNameAndActiveTrue(user.getRole())
                .orElseThrow(()-> new EntityNotFoundException("This role was not found"));
        old.setRole(role);
        if(user.getPassword() != null){
            old.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (userRepository.findUserByEmailAndActiveTrue(user.getEmail()).isEmpty()){
            old.setEmail(user.getEmail());
        }
        return userRepository.save(old);
    }

    @Transactional
    public User deleteUserByUsername(String username){
        User user = findByUsername(username);
        user.setActive(false);
        return userRepository.save(user);
    }




}
