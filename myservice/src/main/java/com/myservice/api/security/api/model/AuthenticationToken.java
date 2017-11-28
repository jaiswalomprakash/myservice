package com.myservice.api.security.api.model;

/**
 * API model for an authentication token.
 *
 * @author omprakash
 */
public class AuthenticationToken {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}