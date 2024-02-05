package com.training.ats.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.MissingResourceException;

/**
 * class to handle exceptions globally. the rest controller applies it across all controllers
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle exceptions of type AuthenticationException such as when user tries to register on an existing acc
     * @param e exception
     * @param request http request
     * @return response entity with server error object
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ServerError> handleException(AuthenticationException e, HttpServletRequest request) {
        ServerError error = new ServerError(
                request.getRequestURI(),
                "Authentication error",
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Handle exceptions with security such as applicant trying to access admin routes
     * @param e security exception thrown
     * @param request http request
     * @return server error with unauthorized access code
     */
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ServerError> handleException(SecurityException e, HttpServletRequest request) {
        ServerError error = new ServerError(
                request.getRequestURI(),
                "Unauthorized. Access denied",
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ServerError> handleException(MissingResourceException e, HttpServletRequest request) {
        ServerError error = new ServerError(
                request.getRequestURI(),
                "Resource not found",
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
