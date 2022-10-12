package com.edti.exceltoxml.Exceptions;

public class QuestionNotExistException extends RuntimeException {
    private String message;

    public QuestionNotExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.getMessage();
    }
}
