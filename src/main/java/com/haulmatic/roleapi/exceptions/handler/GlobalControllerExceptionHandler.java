package com.haulmatic.roleapi.exceptions.handler;

import com.haulmatic.roleapi.models.responses.ApiError;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<ApiError> handleConflict(RuntimeException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
