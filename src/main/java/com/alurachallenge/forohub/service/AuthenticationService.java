package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.constant.MessageConstant;
import com.alurachallenge.forohub.payload.LoginRequest;
import com.alurachallenge.forohub.payload.LoginResponse;
import com.alurachallenge.forohub.payload.MessageResponse;
import com.alurachallenge.forohub.payload.RegisterRequest;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
    MessageResponse register(RegisterRequest registerRequest);
}
