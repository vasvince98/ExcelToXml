package com.edti.exceltoxml.Models.AuxClasses;

public class Questiontext extends AbsFeedback {

    public Questiontext() {
    }

    public Questiontext(String text) {
        setText(text);
        setFormat("html");
    }
}