package com.edti.exceltoxml.Models.AuxClasses;

public class Subquestion extends AbstractAnswer {
    private Answer answer;

    public Subquestion() {}

    public Subquestion(String text) {
        setImageText(text);
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
