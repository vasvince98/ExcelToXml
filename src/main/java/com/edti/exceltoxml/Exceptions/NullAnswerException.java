package com.edti.exceltoxml.Exceptions;

public class NullAnswerException extends RuntimeException {
    private final String message;


    public NullAnswerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
