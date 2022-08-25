package com.edti.exceltoxml.Exceptions;

public class OwnError {
    private String message;

    public OwnError() {}

    public OwnError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
