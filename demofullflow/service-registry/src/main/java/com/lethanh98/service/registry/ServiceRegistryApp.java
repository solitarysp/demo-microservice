package com.lethanh98.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 05-11-2019 13:56  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@SpringBootApplication
@EnableEurekaServer    // Enable eureka server
public class ServiceRegistryApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryApp.class, args);

    }
}
