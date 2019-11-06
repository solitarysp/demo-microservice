package com.lethanh98.service.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 05-11-2019 15:05  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServiceAutheApp {
    @GetMapping("/user")
    public Object user() {

        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceAutheApp.class, args);
    }
}
