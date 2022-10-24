package com.edti.exceltoxml.Models.AuxClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Auxiliary {

    @XmlElement(name = "text")
    private String text;

    public String getText() {
        return this.text;
    }

    public void setImageText(String text) {
        this.text = String.format("<![CDATA[<img src=\"data:image/png;base64," + text + "\"/>]]>");
    }
    public void setSimpleText(String text) {
        this.text = text;
    }

}
