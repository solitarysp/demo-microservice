package com.lethanh98.service.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/10/2019 9:22 AM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@SpringBootApplication
@EnableEurekaClient
public class AuthenApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthenApp.class, args);
    }
}
