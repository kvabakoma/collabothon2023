package com.tigerchamp.collabothon2023backend.model.binding;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;


public class ErrorMessage {
    private String fieldError;
    private String errorMessage;

    public ErrorMessage() {
    }

    public String getFieldError() {
        return fieldError;
    }

    public void setFieldError(String fieldError) {
        this.fieldError = fieldError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
