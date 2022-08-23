package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;

public class IncorrectFeedback extends Text {

    @XmlAttribute
    private String format;

    public IncorrectFeedback(String format) {
        this.format = format;
    }

    public IncorrectFeedback(String text, String format) {
        super(text);
        this.format = format;
    }

}
