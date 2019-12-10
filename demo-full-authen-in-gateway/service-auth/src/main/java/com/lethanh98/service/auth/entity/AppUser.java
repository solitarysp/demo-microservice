package com.lethanh98.service.auth.entity;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 06-11-2019 09:47  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
public class AppUser {
    private Integer id;
    private String username, password;
    private String role;

    public AppUser(Integer id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
