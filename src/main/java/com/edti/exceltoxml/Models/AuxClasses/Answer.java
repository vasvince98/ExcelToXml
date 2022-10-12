package com.edti.exceltoxml.Models.AuxClasses;

import javax.xml.bind.annotation.*;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Answer extends AbsFeedback{
    Feedback feedback;
    @XmlAttribute(name="fraction")
    private String fraction;

    @XmlTransient
    private String idNumber;


    //region Getters and Setters

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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    //endregion


    @Override
    public String toString() {
        return "Answer{" +
                "feedback=" + feedback +
                ", fraction='" + fraction + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", questionText='" + getText() + '\'' +
                '}';
    }


}
