package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.payload.MessageResponse;
import com.alurachallenge.forohub.service.AdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class AdminController {
    private AdminService adminService;

    @PutMapping("/u/{id}")
    public ResponseEntity<MessageResponse> giveAdminRole(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(adminService.giveAdminRolToUser(id));
    }

}
