package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.payload.LoginRequest;
import com.alurachallenge.forohub.payload.LoginResponse;
import com.alurachallenge.forohub.payload.MessageResponse;
import com.alurachallenge.forohub.payload.RegisterRequest;
import com.alurachallenge.forohub.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse loginResponse = authenticationService.login(request);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody @Valid RegisterRequest request) {
        MessageResponse messageResponse = authenticationService.register(request);
        return ResponseEntity.ok(messageResponse);
    }
}
