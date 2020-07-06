package com.haulmatic.roleapi.models.responses;

import com.haulmatic.roleapi.exceptions.ResourceNotFoundException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@ApiModel
public class Response<T> extends ResponseBase {

    @ApiModelProperty(value = "Response result data", position = 2)
    private T data;

    public Response(T t, HttpStatus status) throws Exception {
        super(status);
        if (t == null) {
            throw new ResourceNotFoundException("Internal server error");
        }
        this.data = t;
    }

}