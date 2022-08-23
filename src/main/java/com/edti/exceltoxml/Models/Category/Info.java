package com.edti.exceltoxml.Models.Category;

import com.edti.exceltoxml.Models.Question.Text;

import javax.xml.bind.annotation.XmlAttribute;

public class Info extends Text {
    private String format;

    public Info(String format) {
        this.format = format;
    }

    public Info(String text, String format) {
        super(text);
        this.format = format;
    }

    @XmlAttribute
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
