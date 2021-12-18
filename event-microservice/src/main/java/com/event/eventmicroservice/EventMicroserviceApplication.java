package com.event.eventmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EventMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventMicroserviceApplication.class, args);
    }

}
