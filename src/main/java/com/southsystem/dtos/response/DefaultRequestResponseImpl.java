package com.southsystem.dtos.response;

import io.swagger.annotations.ApiModelProperty;


public class DefaultRequestResponseImpl implements DefaultRequestResponse {

    private String statusCode;
    private String message;

    public DefaultRequestResponseImpl() {
    }

    public DefaultRequestResponseImpl(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
