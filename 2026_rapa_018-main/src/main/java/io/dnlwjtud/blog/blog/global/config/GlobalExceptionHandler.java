package io.dnlwjtud.blog.blog.global.config;

import io.dnlwjtud.blog.blog.global.dto.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CommonResponse<Void>> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(CommonResponse.fail(e.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CommonResponse<Void>> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(CommonResponse.fail(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleGeneralException(Exception e) {
        System.out.println("=== EXCEPTION CAUGHT BY handleGeneralException ===");
        System.out.println("Class: " + e.getClass().getName());
        System.out.println("ClassLoader: " + e.getClass().getClassLoader());
        System.out.println("Message: " + e.getMessage());
        if (e.getCause() != null) {
            System.out.println("Cause Class: " + e.getCause().getClass().getName());
            System.out.println("Cause ClassLoader: " + e.getCause().getClass().getClassLoader());
            System.out.println("Cause Message: " + e.getCause().getMessage());
        }
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonResponse.fail("An unexpected error occurred: " + e.getMessage()));
    }
}