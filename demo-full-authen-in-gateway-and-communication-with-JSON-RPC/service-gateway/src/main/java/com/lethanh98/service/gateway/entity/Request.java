package com.lethanh98.service.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/19/2019 3:36 PM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {
    @JsonProperty(required = false)
    private String authentication;

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
