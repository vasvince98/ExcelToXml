package com.edti.exceltoxml.Models.AuxClasses;


import com.edti.exceltoxml.Models.AdapterCDATA;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Questiontext extends AbsFeedback {

    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String text;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    public Questiontext() {
    }

    public Questiontext(String text) {
        setText(text);
        setFormat("html");
    }
}