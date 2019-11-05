package com.lethanh98.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 05-11-2019 14:23  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@SpringBootApplication
@EnableEurekaClient        // It acts as a eureka client
@EnableZuulProxy
public class ServiceGateWayApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGateWayApp.class, args);

    }
}
