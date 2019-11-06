package com.lethanh98.service.auth.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 27-08-2019 13:47  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@Data
public class ResponseBase<T> implements Serializable {


    private Integer status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> msgError;

    public ResponseBase(Integer status) {
        this.status = status;
    }

    public ResponseBase(Integer status, List<String> msgError) {
        this.status = status;
        this.msgError = msgError;
    }

    public ResponseBase() {
        status = HttpStatus.OK.value();
    }

    public Integer getStatus() {
        return status;
    }
}
