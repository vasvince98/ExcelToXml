package com.edti.exceltoxml.Exceptions;

public class MissingFileException extends RuntimeException {
    private final String message;


    public MissingFileException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
