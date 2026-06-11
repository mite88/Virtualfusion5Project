package io.dnlwjtud.blog.posts.handler;

import io.dnlwjtud.blog.posts.exceptions.UnAuthorizedUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class PostsExceptionHandler {

    @ExceptionHandler(UnAuthorizedUpdateException.class)
    public ResponseEntity<String> handleUnAuthorizedUpdateException(
            UnAuthorizedUpdateException exception
    ) {
        return ResponseEntity.status(
                HttpStatus.FORBIDDEN
        ).body(exception.getMessage());
    }

}
