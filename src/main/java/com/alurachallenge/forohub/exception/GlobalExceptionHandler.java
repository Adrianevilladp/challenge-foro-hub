package com.alurachallenge.forohub.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.sasl.AuthenticationException;
import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundEntityException.class)
    protected final ProblemDetail resolveNotFoundException(NotFoundEntityException ex, WebRequest request) {
        log.error("NotFoundEntityException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setType(URI.create(""));

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected final ProblemDetail resolveMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        problemDetail.setType(URI.create(""));
        Map<String, Object> properties = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> properties.put(error.getField(), error.getDefaultMessage()));
        problemDetail.setProperties(properties);
        log.warn("MethodArgumentNotValidException: Message - {}, Properties - {}, Path - {}", ex.getMessage(), properties, request.getDescription(false));


        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected final ProblemDetail resolveConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {
        log.error("ConstraintViolationException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setType(URI.create(""));

        return problemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected final ProblemDetail resolveIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        log.error("IllegalArgumentException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setType(URI.create(""));

        return problemDetail;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected final ProblemDetail resolveMissingServletRequestParameterException(
            MissingServletRequestParameterException ex, WebRequest request) {
        log.warn("MissingServletRequestParameterException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setType(URI.create(""));

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected final ProblemDetail resolveMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String message = String.format("Parameter '%s' with value '%s' is incorrect. Please check the documentation for the correct parameter type.",
                ex.getName(), ex.getValue());
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
        problemDetail.setType(URI.create(""));
        log.warn("MethodArgumentTypeMismatchException: Message - {}, Path - {}", message, request.getDescription(false));

        return problemDetail;
    }

    @ExceptionHandler(AuthenticationException.class)
    protected final ProblemDetail resolveAuthenticationException(AuthenticationException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        log.error("AuthenticationException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        return problemDetail;
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected final ProblemDetail resolveAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        log.error("AccessDeniedException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    protected final ProblemDetail resolveGlobalException(Exception ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        log.error("Exception: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        return problemDetail;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected final ProblemDetail resolveHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
        log.error("HttpRequestMethodNotSupportedException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        return problemDetail;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected final ProblemDetail resolveHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
        log.error("HttpMediaTypeNotSupportedException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected final ProblemDetail resolveHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        log.error("HttpMessageNotReadableException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    protected final ProblemDetail resolveHttpMessageNotWritableException(HttpMessageNotWritableException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        log.error("HttpMessageNotWritableException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        return problemDetail;
    }

    @ExceptionHandler(TypeMismatchException.class)
    protected final ProblemDetail resolveTypeMismatchException(TypeMismatchException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        log.error("TypeMismatchException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        return problemDetail;
    }

    @ExceptionHandler(BindException.class)
    protected final ProblemDetail resolveBindException(BindException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        log.error("BindException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        return problemDetail;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected final ProblemDetail resolveNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "No handler found for the requested URL");
        log.error("NoHandlerFoundException: Message - {}, Path - {}", ex.getMessage(), request.getDescription(false));
        return problemDetail;
    }

}
