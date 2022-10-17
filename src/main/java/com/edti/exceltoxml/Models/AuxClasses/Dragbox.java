package com.edti.exceltoxml.Models.AuxClasses;

import javax.xml.bind.annotation.XmlTransient;

public class Dragbox extends AbstractAnswer {
    private String group;

    @XmlTransient
    private String answerNumber;

    public Dragbox() {}

    public Dragbox(String text, String group) {
        setText(text);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(String answerNumber) {
        this.answerNumber = answerNumber;
    }
}
