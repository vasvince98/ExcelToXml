package com.edti.exceltoxml.Models.AuxClasses;

import javax.xml.bind.annotation.XmlAttribute;


public class Info extends Auxiliary {

    private String text;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if(!(obj instanceof Info info)) {
            return false;
        }

        return info.getText().equals(this.getText());
    }
}
