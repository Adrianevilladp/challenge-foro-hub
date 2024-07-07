package com.alurachallenge.forohub.payload;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String message;
    private String tokenType = "Bearer";

    public LoginResponse(String message) {
        this.message = message;
    }
}
