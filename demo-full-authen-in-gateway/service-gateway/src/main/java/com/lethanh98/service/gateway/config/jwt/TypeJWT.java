package com.lethanh98.service.gateway.config.jwt;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 21-09-2019 11:58  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
public enum TypeJWT {
    TOKEN(1, "accessToken"),
    REFRESH_TOKEN(2, "refresh accessToken"),
    ;
    private Integer value;
    private String name;

    TypeJWT(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static final TypeJWT getEnumByValue(Integer value) {
        for (TypeJWT obj : TypeJWT.values()) {
            if (obj.getValue().equals(value))
                return obj;
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
