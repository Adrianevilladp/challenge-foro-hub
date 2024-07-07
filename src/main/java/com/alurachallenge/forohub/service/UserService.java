package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.entity.User;
import com.alurachallenge.forohub.payload.RegisterRequest;

public interface UserService {
    User create(RegisterRequest registerRequest);
    User getById(Long id);
}
