package com.demo.MiniProject1.service;

import com.demo.MiniProject1.exception.AuthenticationException;

public class AuthServiceImpl implements AuthService{


private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    @Override
    public void login(String username, String password) {
        if (!(USERNAME.equals(username) && PASSWORD.equals(password))) {
            throw new AuthenticationException("Invalid credentials.");
        }
    }

}
