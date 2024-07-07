package com.alurachallenge.forohub.payload;

import com.alurachallenge.forohub.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @ValidPassword
        @NotBlank
        String password
) {
}
