package com.edti.exceltoxml.Models.AuxClasses;

import javax.xml.bind.annotation.XmlAttribute;


public class Info extends Auxiliary {

    @XmlAttribute(name="format")
    private String format;

    public Info(String text) {
        setSimpleText(text);
        this.format = "moodle_auto_format";
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
