package com.haulmatic.roleapi.models.responses;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@ApiModel
public class ApiError extends ResponseBase {

    private String message;

    public ApiError(HttpStatus status) {
        super(status);
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this(status);
        this.message = message;
    }
}