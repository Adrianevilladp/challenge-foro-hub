package com.alurachallenge.forohub.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotFoundEntityException extends RuntimeException {
    private String message;
}
