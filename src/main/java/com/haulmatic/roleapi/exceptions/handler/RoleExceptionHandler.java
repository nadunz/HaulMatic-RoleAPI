package com.haulmatic.roleapi.exceptions.handler;

import com.haulmatic.roleapi.exceptions.NICAlreadyExistsException;
import com.haulmatic.roleapi.exceptions.ResourceNotFoundException;
import com.haulmatic.roleapi.exceptions.UnavailableRoleTypeException;
import com.haulmatic.roleapi.exceptions.DataValidationException;
import com.haulmatic.roleapi.models.responses.ApiError;
import com.mongodb.MongoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RoleExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> resourceNotFound(ResourceNotFoundException e) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> validationExceptions(DataValidationException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnavailableRoleTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> unavailableRoleTypeExceptions(UnavailableRoleTypeException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NICAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ApiError> nicAlreadyExistsExceptions(NICAlreadyExistsException e) {
        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MongoException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ApiError> handleMongoExceptions(MongoException e) {
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e),
                HttpStatus.NOT_ACCEPTABLE);
    }

}
