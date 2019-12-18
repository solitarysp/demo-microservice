package com.lethanh98.service.gateway.config.jwt;

import org.springframework.security.core.GrantedAuthority;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 03-09-2019 15:20  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN("ROLE_ADMIN", 0)
    , ROLE_CLIENT("ROLE_CLIENT", 1)
    , ROLE_ANONYMOUS("ROLE_ANONYMOUS", 2)
    ;
    private String authority;
    private Integer valueMagic;

    Role(String authority, Integer valueMagic) {
        this.authority = authority;
        this.valueMagic = valueMagic;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public Integer getValueMagic() {
        return valueMagic;
    }}
