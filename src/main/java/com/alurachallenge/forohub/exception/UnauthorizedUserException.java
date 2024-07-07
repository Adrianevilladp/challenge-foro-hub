package com.alurachallenge.forohub.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UnauthorizedUserException extends RuntimeException{
    private String message;
}
