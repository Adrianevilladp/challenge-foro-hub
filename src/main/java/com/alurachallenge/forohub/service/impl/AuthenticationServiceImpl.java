package com.alurachallenge.forohub.service.impl;

import com.alurachallenge.forohub.constant.MessageConstant;
import com.alurachallenge.forohub.entity.User;
import com.alurachallenge.forohub.payload.LoginRequest;
import com.alurachallenge.forohub.payload.LoginResponse;
import com.alurachallenge.forohub.payload.MessageResponse;
import com.alurachallenge.forohub.payload.RegisterRequest;
import com.alurachallenge.forohub.security.JwtTokenProvider;
import com.alurachallenge.forohub.service.AuthenticationService;
import com.alurachallenge.forohub.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()));
        log.info("User {} logged in successfully!", request.email());
        String jwt = jwtTokenProvider.generateToken(authentication);

        return new LoginResponse(jwt);
    }

    @Override
    public MessageResponse register(RegisterRequest registerRequest) {
        User userToRegister = userService.create(registerRequest);
        log.info("User {} registered successfully!", userToRegister.getId());
        return new MessageResponse(MessageConstant.USER_REGISTERED);
    }
}
