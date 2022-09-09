package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;

public class GeneralFeedback extends Text {

    @XmlAttribute
    private String format;

    public GeneralFeedback() {}

    public GeneralFeedback(String format) {
        this.format = format;
    }

    public GeneralFeedback(String text, String format) {
        super(text);
        this.format = format;
    }
}
