package com.edti.exceltoxml.Models.QuestionTypes;

import com.edti.exceltoxml.Models.AuxClasses.*;
import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public abstract class RealQuestion extends Question {
    Name name;
    Questiontext questiontext;
    Generalfeedback generalfeedback;
    private String defaultgrade;
    private String penalty;
    private String hidden;

    private List<Answer> answer = new ArrayList<>();
    private List<Subquestion> subquestion = new ArrayList<>();
    private List<Dragbox> dragbox = new ArrayList<>();



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
            case ddwtos -> {
                List<Dragbox> answerList = new ArrayList<>();
                answer.forEach((a) -> answerList.add((Dragbox) a));
                this.dragbox = answerList;
            }
        }

    }

    protected abstract void initSimpleInstance(HashMap<String, String> data);
    protected abstract void initImageInstance(HashMap<String, String> data);


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
