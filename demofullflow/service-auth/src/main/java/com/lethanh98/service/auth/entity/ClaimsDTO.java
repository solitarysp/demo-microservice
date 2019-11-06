package com.lethanh98.service.auth.entity;

import lombok.Data;

import java.util.List;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 15-10-2019 14:23  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@Data
public class ClaimsDTO {
    private String sub;
    private Integer type;
    private String iat;
    private String exp;
    private List<String> role;
}
