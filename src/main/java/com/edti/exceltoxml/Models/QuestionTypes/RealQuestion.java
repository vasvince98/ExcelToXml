package com.edti.exceltoxml.Models.QuestionTypes;

import com.edti.exceltoxml.Models.AuxClasses.*;
import com.edti.exceltoxml.Models.Enums.QType;

import java.util.ArrayList;
import java.util.List;


public abstract class RealQuestion extends Question {
    Name name;
    Questiontext questiontext;
    Generalfeedback generalfeedback;
    private String defaultgrade;
    private String penalty;
    private String hidden;

    private List<Answer> answer = new ArrayList<>();
    private List<Subquestion> subquestion = new ArrayList<>();


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

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<AbstractAnswer> answer, QType type) {
        switch (type) {
            case multichoice, truefalse -> {
                List<Answer> answerList = new ArrayList<>();
                answer.forEach((a) -> answerList.add((Answer) a));
                this.answer = answerList;
            }
            case matching -> {
                List<Subquestion> answerList = new ArrayList<>();
                answer.forEach((a) -> answerList.add((Subquestion) a));
                this.subquestion = answerList;
            }
        }

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
