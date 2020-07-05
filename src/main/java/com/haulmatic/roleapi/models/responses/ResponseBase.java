package com.haulmatic.roleapi.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@ApiModel
public class ResponseBase {

    @ApiModelProperty(value = "Https status code", example = "OK", position = 0)
    private HttpStatus status;

    @ApiModelProperty(value = "Date & time", position = 1)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date timestamp;

    public ResponseBase(HttpStatus status) {
        this.status = status;
        this.timestamp = new Date();
    }
}
