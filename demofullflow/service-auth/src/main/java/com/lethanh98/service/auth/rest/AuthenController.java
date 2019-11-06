package com.lethanh98.service.auth.rest;

import com.lethanh98.service.auth.entity.LoginDTO;
import com.lethanh98.service.auth.entity.LoginRP;
import com.lethanh98.service.auth.entity.LoginRQ;
import com.lethanh98.service.auth.entity.ResponseBase;
import com.lethanh98.service.auth.service.impl.AuthenAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Objects;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 06-11-2019 09:48  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@RestController
public class AuthenController {
    @Autowired
    AuthenAccountServiceImpl authenAccountServiceImpl;

    @PostMapping("/login")
    public ResponseBase login(@RequestBody LoginRQ loginRQ) {

        LoginRP loginRP = new LoginRP();
        LoginDTO loginDTO = new LoginDTO();
        try {
            loginDTO.setAccessToken(authenAccountServiceImpl.login(loginRQ.getUsername(), loginRQ.getPassword()));
            if (Objects.nonNull(loginDTO.getAccessToken())) {
                loginDTO.setRefreshToken(authenAccountServiceImpl.createRefreshToken(loginRQ.getUsername()));
            }
            loginRP.setData(loginDTO);
        } catch (Exception e) {
            loginRP.setStatus(200);
            loginRP.setMsgError(Collections.singletonList("Invalid username/password supplied"));
        }
        return loginRP;
    }

    @GetMapping("/user/info")
    public Authentication login() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
