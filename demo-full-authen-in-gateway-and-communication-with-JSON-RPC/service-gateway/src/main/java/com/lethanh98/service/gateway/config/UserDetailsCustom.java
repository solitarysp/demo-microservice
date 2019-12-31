package com.lethanh98.service.gateway.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/31/2019 5:20 PM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsCustom {
    private static final Log logger = LogFactory.getLog(User.class);
    private String password;
    private String username;
    private List<Authority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

}

@Data
class Authority implements GrantedAuthority {
    private String authority;

}
