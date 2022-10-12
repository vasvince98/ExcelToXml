package com.edti.exceltoxml.Models.AuxClasses;

public class SubQuestion extends AbstractAnswer {
    private Answer answer;

    public SubQuestion() {}

    public SubQuestion(String text) {
        setText(text);
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
