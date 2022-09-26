package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;

public class Answer extends Text {
    private Feedback feedback;


    @XmlAttribute
    private String fraction;
    @XmlAttribute
    private String format;

    public Answer() {}

    public Answer(String text) {
        super(text);
    }

    public Answer(Feedback feedback, String fraction, String format) {
        this.feedback = feedback;
        this.fraction = fraction;
        this.format = format;
    }

    public Answer(String text, Feedback feedback, String fraction, String format) {
        super(text);
        this.feedback = feedback;
        this.fraction = fraction;
        this.format = format;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }


    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public void setFormat(String format) {
        this.format = format;
    }

//    @Override
//    public void setText(String text) {
//        super.setText(String.format("<img src=\"data:image/png;base64,%s\"/>", text));
//    }



    @Override
    public String toString() {
        return "Answer{" +
                "feedback=" + feedback +
                '}';
    }
}
