package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

public class QuestionText extends Text {

    @XmlAttribute
    private String format;

    @XmlTransient
    private boolean isPicture;

    public QuestionText() {}

    public QuestionText(String format) {
        this.format = format;
    }

    public QuestionText(String format, String text) {
        super(String.format("<img src=\"data:image/png;base64,%s\"/>", text));
        this.format = format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
