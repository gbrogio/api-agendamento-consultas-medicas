package com.example.demo.service.Utils;

import java.util.Map;

public class ErrorResponse {
    private String error;
    private String message;
    private Map<String, String> fields;

    public ErrorResponse(String error, String message, Map<String, String> fields) {
        this.error = error;
        this.message = message;
        this.fields = fields;
    }

    // Getters and Setters
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
