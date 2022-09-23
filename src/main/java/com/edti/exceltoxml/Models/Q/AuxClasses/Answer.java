package com.edti.exceltoxml.Models.Q.AuxClasses;

public class Answer extends AbsFeedback{
    Feedback feedback;
    private String _fraction;

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public String get_fraction() {
        return _fraction;
    }

    public void set_fraction(String _fraction) {
        this._fraction = _fraction;
    }
}
