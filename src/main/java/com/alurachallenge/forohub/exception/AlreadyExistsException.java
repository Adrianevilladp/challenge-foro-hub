package com.alurachallenge.forohub.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AlreadyExistsException extends RuntimeException{
    private String message;
}
