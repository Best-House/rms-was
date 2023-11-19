package com.bh.rms.advice;

import com.bh.rms.domain.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Component
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(Exception e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorResponse("Exception: " + e.getClass().getName()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorResponse("Exception: " + e.getClass().getName()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private String message;
    }
}
