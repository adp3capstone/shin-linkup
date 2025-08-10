package com.ethan.adatingapp.util;

import com.ethan.adatingapp.domain.User;

public class AuthResponse {
    private String token;
    private UserDTO user;

    public AuthResponse(String token,UserDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserDTO getUser() {
        return user;
    }
}
