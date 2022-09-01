package com.edti.exceltoxml.Exceptions;

public class MissingHeaderException extends RuntimeException {
    private final String message;


    public MissingHeaderException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
