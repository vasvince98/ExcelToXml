package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;

public class QuestionText extends Text {

    @XmlAttribute
    private String format;

    public QuestionText(String format) {
        this.format = format;
    }

    public QuestionText(String text, String format) {
        super(text);
        this.format = format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
