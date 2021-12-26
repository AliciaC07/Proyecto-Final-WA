package com.event.eventmicroservice;

import com.event.eventmicroservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class EventMicroserviceApplication {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    EventService eventService;



    public static void main(String[] args) {
        SpringApplication.run(EventMicroserviceApplication.class, args);
    }

    @PostConstruct
    public void insertEvents(){
        eventService.insertEvents();
    }
}
