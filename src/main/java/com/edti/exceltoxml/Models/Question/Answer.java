package com.edti.exceltoxml.Models.Question;

public class Answer extends Text {
    private Feedback feedback;

    public Answer(Feedback feedback) {
        this.feedback = feedback;
    }

    public Answer(String text, Feedback feedback) {
        super(text);
        this.feedback = feedback;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "feedback=" + feedback +
                '}';
    }
}
