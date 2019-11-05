package com.lethanh98.service.gateway.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 05-11-2019 13:50  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@SpringBootApplication
@EnableConfigServer
public class ServiceConfigApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceConfigApp.class).run(args);

    }
}
