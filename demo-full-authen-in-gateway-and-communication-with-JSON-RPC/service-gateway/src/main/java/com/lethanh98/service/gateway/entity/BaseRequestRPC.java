package com.lethanh98.service.gateway.entity;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/31/2019 4:20 PM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
public class BaseRequestRPC<T> {
    private String id;
    private String jsonrpc;
    private String method;
    private T params;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
