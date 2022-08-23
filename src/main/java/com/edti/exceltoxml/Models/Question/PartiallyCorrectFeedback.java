package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;

public class PartiallyCorrectFeedback extends Text {

    @XmlAttribute
    private String format;

    public PartiallyCorrectFeedback(String format) {
        this.format = format;
    }

    public PartiallyCorrectFeedback(String text, String format) {
        super(text);
        this.format = format;
    }

}
