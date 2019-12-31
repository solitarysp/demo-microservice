package com.lethanh98.service.gateway.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lethanh98.service.gateway.config.jwt.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/31/2019 4:58 PM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsernamePasswordAuthenticationTokenCustom {
    UserDetailsCustom principal;
  //  List<Role> authorities;


    public UserDetailsCustom getPrincipal() {
        return principal;
    }

    public void setPrincipal(UserDetailsCustom principal) {
        this.principal = principal;
    }
}
