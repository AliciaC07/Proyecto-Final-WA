package com.eurekaserver.eurekaerver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaErverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaErverApplication.class, args);
    }

}
