package com.example.midterneconermic.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;

    public ErrorResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
