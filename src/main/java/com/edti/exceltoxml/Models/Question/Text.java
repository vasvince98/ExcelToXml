package com.edti.exceltoxml.Models.Question;

import com.edti.exceltoxml.Models.AdapterCDATA;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Text {


    private String text;

    public Text() {}

    public Text(String text) {
        this.text = text;
    }

    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Text{" +
                "text='" + text + '\'' +
                '}';
    }
}
