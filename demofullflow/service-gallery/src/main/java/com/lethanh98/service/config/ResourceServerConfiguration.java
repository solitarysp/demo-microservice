package com.lethanh98.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 06-11-2019 11:00  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
//Imports removed for conciseness
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
    }
}
