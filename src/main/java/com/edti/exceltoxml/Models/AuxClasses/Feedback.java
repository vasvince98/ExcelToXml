package com.edti.exceltoxml.Models.AuxClasses;

public class Feedback extends AbsFeedback {
    private String text;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
    public Feedback(String text) {
        setText(text);
    }


}
