package com.lethanh98.service.auth.rpc.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.lethanh98.service.auth.config.jwt.JwtTokenProvider;
import com.lethanh98.service.auth.request.LoginRQ;
import com.lethanh98.service.auth.response.LoginRP;
import com.lethanh98.service.auth.response.ResponseBase;
import com.lethanh98.service.auth.response.view.LoginModelView;
import com.lethanh98.service.auth.rpc.AuthenServiceRpc;
import com.lethanh98.service.auth.service.impl.AuthenAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/18/2019 2:33 PM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@Service
@AutoJsonRpcServiceImpl
public class AuthenServiceRpcImpl implements AuthenServiceRpc {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenAccountServiceImpl authenAccountServiceImpl;


    @Override
    public ResponseBase login(LoginRQ loginRQ) {
        System.out.println("dd");
        LoginRP loginRP = new LoginRP();
        LoginModelView loginModelView = new LoginModelView();
        try {
            loginModelView.setAccessToken(authenAccountServiceImpl.login(loginRQ.getUsername(), loginRQ.getPassword()));
            if (Objects.nonNull(loginModelView.getAccessToken())) {
                loginModelView.setRefreshToken(authenAccountServiceImpl.createRefreshToken(loginRQ.getUsername()));
               // loginModelView.setTokenType("Bearer");
            }
            loginRP.setData(loginModelView);
        } catch (Exception e) {
            loginRP.setStatus(200);
            loginRP.setMsgError(Collections.singletonList("Invalid username/password supplied"));
        }
        return loginRP;
    }

    @Override
    public String infoUsingApp(String token) {
        info(token);
        try {
            return objectMapper.writeValueAsString(SecurityContextHolder.getContext().getAuthentication());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Autowired
    ObjectMapper objectMapper;
    @Override
    public Authentication info(String token) {
        System.out.println("info");
        try {
            if (token != null && Objects.nonNull(jwtTokenProvider.validateTokenReturnUserName(token))) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            return SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception e) {
          /*  try {
                return objectMapper.writeValueAsString(SecurityContextHolder.getContext().getAuthentication());
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }*/
        }
        return null;
    }
}
