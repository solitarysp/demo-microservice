package com.lethanh98.service.gateway.config.jwt;

import com.lethanh98.service.gateway.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MyUserDetails implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // có thể call database để check lấy data..etc..
        // hard coding the users. All passwords must be encoded.
        final List<AppUser> users = Arrays.asList(
                new AppUser(1, "123", "123", "USER"),
                new AppUser(2, "1234", "1234", "ADMIN")
        );


        for (AppUser appUser : users) {
            if (appUser.getUsername().equals(username)) {

                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());

                // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
                // And used by auth manager to verify and check user authentication.
                return org.springframework.security.core.userdetails.User//
                        .withUsername(appUser.getUsername())//
                        .password(passwordEncoder.encode(appUser.getPassword()))//
                        .authorities(grantedAuthorities)//
                        .accountExpired(false)//
                        .accountLocked(false)//
                        .credentialsExpired(false)//
                        .disabled(false)//
                        .build();
            }
        }

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: " + username + " not found");


    }

}
