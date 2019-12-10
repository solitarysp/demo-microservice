package com.lethanh98.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/10/2019 9:44 AM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class GalleryApp {
    public static void main(String[] args) {
        SpringApplication.run(GalleryApp.class, args);
    }

}
@Configuration
class RestTemplateConfig {

    @Bean
    @LoadBalanced        // Load balance between service instances running at different ports.
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        return template;
    }
}
