package com.lethanh98.service.auth.rpc;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.lethanh98.service.auth.request.LoginRQ;
import com.lethanh98.service.auth.response.ResponseBase;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/18/2019 2:31 PM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@JsonRpcService("/authen")
public interface AuthenServiceRpc {
    public ResponseBase login(@JsonRpcParam(value = "loginRQ") LoginRQ loginRQ);

    @GetMapping("/user/info")
    public Authentication info(@JsonRpcParam(value = "token") String token);
}
