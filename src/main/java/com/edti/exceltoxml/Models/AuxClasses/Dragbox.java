package com.edti.exceltoxml.Models.AuxClasses;

import javax.xml.bind.annotation.XmlTransient;

public class Dragbox extends AbstractAnswer {
    private int group;

    @XmlTransient
    private String answerNumber;

    public Dragbox() {}

    public Dragbox(String text, int group) {
        setText(text);
        this.group = group;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(String answerNumber) {
        this.answerNumber = answerNumber;
    }
}
