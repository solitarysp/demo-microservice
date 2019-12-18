package com.lethanh98.registry;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/10/2019 9:17 AM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryApp {
    public static void main(String[] args) {
        SpringApplication.run(RegistryApp.class, args);
    }
}
