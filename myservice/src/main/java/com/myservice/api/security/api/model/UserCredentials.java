package com.myservice.api.security.api.model;

/**
 * API model user credentials.
 *
 * @author omprakash
 */
public class UserCredentials {

    private String username;
    private String password;

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
}