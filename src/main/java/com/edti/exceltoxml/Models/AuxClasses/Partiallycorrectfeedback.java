package com.edti.exceltoxml.Models.AuxClasses;

public class Partiallycorrectfeedback extends AbsFeedback {
    private String text;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}