package com.lethanh98.service.gateway.rpc;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.lethanh98.service.gateway.response.ResponseBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/18/2019 2:31 PM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
public interface AuthenServiceRpc {
    public ResponseBase login(String loginRQ);
    public String infoUsingApp(String token);
    public Authentication info(String token);
}

@Configuration
class AuthenServiceRpcConfig {
    @Bean
    public AuthenServiceRpc client() throws MalformedURLException {
        String endpoint = "http://localhost:9101/authen";
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), AuthenServiceRpc.class, new JsonRpcHttpClient(new URL(endpoint), new HashMap<>()));
    }
}
