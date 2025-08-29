package com.ethan.adatingapp.util;

import com.ethan.adatingapp.domain.User;

//what the backend sends to the client after login/signup
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
