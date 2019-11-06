package com.lethanh98.service.auth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 19-09-2019 14:24  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@Data
public class LoginDTO {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Long expiresIn;
}
