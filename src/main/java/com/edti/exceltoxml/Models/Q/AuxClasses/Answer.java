package com.edti.exceltoxml.Models.Q.AuxClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Answer extends AbsFeedback{
    Feedback feedback;
    @XmlAttribute(name="fraction")
    private String fraction;

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }
}
