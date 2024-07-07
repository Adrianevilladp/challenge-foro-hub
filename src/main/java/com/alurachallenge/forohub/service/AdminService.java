package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.payload.MessageResponse;

public interface AdminService {
    MessageResponse giveAdminRolToUser(Long id);
}
