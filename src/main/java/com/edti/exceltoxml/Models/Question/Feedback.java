package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;

public class Feedback extends Text {

    @XmlAttribute
    private String format;

    public Feedback(String format) {
        this.format = format;
    }

    public Feedback(String text, String format) {
        super(text);
        this.format = format;
    }

}
