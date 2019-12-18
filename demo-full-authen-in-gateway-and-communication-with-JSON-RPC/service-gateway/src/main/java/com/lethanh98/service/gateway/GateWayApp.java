package com.lethanh98.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/10/2019 9:30 AM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@SpringBootApplication
@EnableEurekaClient        // It acts as a eureka client
@EnableZuulProxy
public class GateWayApp {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApp.class, args);
    }
}
