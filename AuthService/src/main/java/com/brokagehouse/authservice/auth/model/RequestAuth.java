package com.brokagehouse.authservice.auth.model;

public class RequestAuth {
    private String username;
    private String password;

    public RequestAuth() {
    }

    public RequestAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
