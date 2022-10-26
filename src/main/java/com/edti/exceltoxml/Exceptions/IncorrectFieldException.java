package com.edti.exceltoxml.Exceptions;

public class IncorrectFieldException extends RuntimeException {
    private final String message;


    public IncorrectFieldException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
