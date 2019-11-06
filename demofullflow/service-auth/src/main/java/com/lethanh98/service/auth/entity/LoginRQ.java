package com.lethanh98.service.auth.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 03-09-2019 14:38  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@Data
public class LoginRQ {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
}
