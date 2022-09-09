package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;

public class CorrectFeedback extends Text {

    @XmlAttribute
    private String format;

    public CorrectFeedback() {
    }

    public CorrectFeedback(String format) {
        this.format = format;
    }

    public CorrectFeedback(String text, String format) {
        super(text);
        this.format = format;
    }

}
