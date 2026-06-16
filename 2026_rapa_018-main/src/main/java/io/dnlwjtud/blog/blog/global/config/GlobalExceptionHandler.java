package io.dnlwjtud.blog.blog.global.config;

import io.dnlwjtud.blog.blog.global.code.ResponseCode;
import io.dnlwjtud.blog.blog.global.dto.CommonResponse;
import io.dnlwjtud.blog.blog.global.exception.BusinessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponse<Void>> handleBusinessException(BusinessException e) {
        ResponseCode responseCode = e.getResponseCode();
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(CommonResponse.fail(responseCode, e.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CommonResponse<Void>> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity
                .status(ResponseCode.USER_NOT_FOUND.getHttpStatus())
                .body(CommonResponse.fail(ResponseCode.USER_NOT_FOUND));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CommonResponse<Void>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity
                .status(ResponseCode.DUPLICATE_MEMBER.getHttpStatus())
                .body(CommonResponse.fail(ResponseCode.DUPLICATE_MEMBER));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleGeneralException(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ResponseCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(CommonResponse.fail(ResponseCode.INTERNAL_SERVER_ERROR));
    }
}