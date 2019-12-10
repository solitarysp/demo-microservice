package com.lethanh98.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/10/2019 9:14 AM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigApp.class).run(args);

    }
}
