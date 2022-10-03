package com.edti.exceltoxml.Models.Q.QuestionTypes;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Generalfeedback;
import com.edti.exceltoxml.Models.Q.AuxClasses.Name;
import com.edti.exceltoxml.Models.Q.AuxClasses.Questiontext;

import java.util.ArrayList;

public abstract class RealQuestion extends Question {
    Name name;
    Questiontext questiontext;
    Generalfeedback generalfeedback;
    private String defaultgrade;
    private String penalty;
    private String hidden;

    private ArrayList<Answer> answer = new ArrayList<>();

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Questiontext getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(Questiontext questiontext) {
        this.questiontext = questiontext;
    }

    public Generalfeedback getGeneralfeedback() {
        return generalfeedback;
    }

    public void setGeneralfeedback(Generalfeedback generalfeedback) {
        this.generalfeedback = generalfeedback;
    }

    public String getDefaultgrade() {
        return defaultgrade;
    }

    public void setDefaultgrade(String defaultgrade) {
        this.defaultgrade = defaultgrade;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public ArrayList<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Answer> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "RealQuestion{" +
                "name=" + name +
                ", questiontext=" + questiontext +
                ", generalfeedback=" + generalfeedback +
                ", defaultgrade='" + defaultgrade + '\'' +
                ", penalty='" + penalty + '\'' +
                ", hidden='" + hidden + '\'' +
                ", answer=" + answer +
                '}';
    }
}
