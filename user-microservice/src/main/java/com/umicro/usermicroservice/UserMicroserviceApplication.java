package com.umicro.usermicroservice;

import com.umicro.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@EnableDiscoveryClient
@SpringBootApplication
public class UserMicroserviceApplication {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }



    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(UserMicroserviceApplication.class, args);
        UserService userService = (UserService) applicationContext.getBean("userService");
        if (userService.verify("admin").isEmpty()){
            userService.insertAdmin();
        }

    }


}
