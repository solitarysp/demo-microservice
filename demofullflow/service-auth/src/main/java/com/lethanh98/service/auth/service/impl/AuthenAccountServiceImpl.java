package com.lethanh98.service.auth.service.impl;

import com.lethanh98.service.auth.config.jwt.JwtTokenProvider;
import com.lethanh98.service.auth.config.jwt.Role;
import com.lethanh98.service.auth.model.ClaimsDTO;
import com.lethanh98.service.auth.exception.CustomException;
import com.lethanh98.service.auth.service.AuthenAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 06-11-2019 09:49  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@Service
public class AuthenAccountServiceImpl implements AuthenAccountService {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, Arrays.asList(Role.ROLE_ADMIN));
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String createRefreshToken(String username) {
        return jwtTokenProvider.createRefreshToken(username);
    }

    public String refreshToken(String username) {
        return jwtTokenProvider.createToken(username, Arrays.asList(Role.ROLE_ADMIN));
    }

    public String validateRefreshTokenReturnUserName(String token) {
        return jwtTokenProvider.validateRefreshTokenReturnUserName(token);
    }

    public ClaimsDTO getClaimsDTOFromToken(String token) {
        return jwtTokenProvider.getClaimsDTOFromToken(token);
    }
}
