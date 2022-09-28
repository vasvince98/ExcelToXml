package com.edti.exceltoxml.Models.Q.AuxClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


public class Info extends Auxiliary{
    @XmlAttribute(name="format")
    private String format;

    public Info(String text) {
        this.format = "moodle_auto_format";
        setText(text);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
