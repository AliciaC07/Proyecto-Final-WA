package com.umicro.usermicroservice.service;

import com.umicro.usermicroservice.models.Role;
import com.umicro.usermicroservice.models.User;
import com.umicro.usermicroservice.models.dtos.EmailSend;
import com.umicro.usermicroservice.models.dtos.UserDTO;
import com.umicro.usermicroservice.models.dtos.UserRecieveDTO;
import com.umicro.usermicroservice.repository.RoleRepository;
import com.umicro.usermicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    RestTemplate restTemplate;

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
    public Optional<User> verify(String userName){
        return userRepository.findUserByUsernameAndActiveTrue(userName);
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

    public Iterable<UserRecieveDTO> findUserByRol(){
        Role role = roleRepository.findByNameAndActiveTrue("Employee")
                .orElseThrow(()-> new EntityNotFoundException("This role was not found"));
        Iterable<User> users = userRepository.findUserByRoleAndActiveTrue(role);
        List<UserRecieveDTO> userDTOS = new ArrayList<>();
        for (User u : users){
            UserRecieveDTO userDTO = new UserRecieveDTO();
            userDTO.setEmail(u.getEmail());
            userDTO.setRole(u.getRole().getName());
            userDTO.setPassword(u.getPassword());
            userDTO.setName(u.getName());
            userDTO.setUsername(u.getUsername());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Transactional
    public User deleteUserByUsername(String username){
        User user = findByUsername(username);
        user.setActive(false);
        return userRepository.save(user);
    }

    public String notificationSender(EmailSend emailSend) {

        HttpEntity<EmailSend> bodyRequest = new HttpEntity<>(emailSend);
        return restTemplate.exchange("http://NOTIFICATION-MICROSERVICE/noti/user-notification",
                HttpMethod.POST,
                bodyRequest,
                String.class).getBody();
    }

    @Transactional
    public void insertAdmin(){
        Role role = new Role();
        Role role1 = new Role();
        Role role2 = new Role();
        role.setName("Admin");
        role1.setName("Client");
        role2.setName("Employee");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        roles.add(role1);
        roles.add(role2);
        roleRepository.saveAll(roles);
        User user = new User();
        user.setName("Alicia");
        user.setPassword("admin123");
        user.setLastName("Cruz");
        user.setUsername("admin");
        user.setEmail("aliciacruzj0307@outlook.com");
        user.setRole(role);
        save(user);
        User user1 = new User();
        user1.setName("Ruben");
        user1.setPassword("admin123");
        user1.setLastName("Diaz");
        user1.setUsername("rb00");
        user1.setEmail("rubenosmani.dc@gmail.com");
        user1.setRole(role2);
        save(user1);
    }




}
