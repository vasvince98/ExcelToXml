package com.edti.exceltoxml.Exceptions;

public class MissingDataException extends RuntimeException {

    private final String message;


    public MissingDataException(String message) {
       this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
