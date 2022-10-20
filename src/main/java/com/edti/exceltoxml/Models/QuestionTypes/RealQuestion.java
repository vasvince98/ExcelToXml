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

    //region Fields
    private Name name;
    private Questiontext questiontext;
    private Generalfeedback generalfeedback;
    private String defaultgrade;
    private String penalty;
    private String hidden;

    private List<Answer> answer = new ArrayList<>();
    private List<Subquestion> subquestion = new ArrayList<>();
    private List<Dragbox> dragbox = new ArrayList<>();

    //endregion


    //region Getters and Setters

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

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    public List<Subquestion> getSubquestion() {
        return subquestion;
    }

    public void setSubquestion(List<Subquestion> subquestion) {
        this.subquestion = subquestion;
    }

    public List<Dragbox> getDragbox() {
        return dragbox;
    }

    public void setDragbox(List<Dragbox> dragbox) {
        this.dragbox = dragbox;
    }

    //endregion

    /**
     *
     * Set the answer based on the provided question type.
     *
     * @param answer list with answers with a specific type.
     * @param type The type of question associated with the answer.
     */
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

    /**
     *
     * This method will be called when you want to do a simple conversion, without image generating.
     *
     * @param data The provided data map will be converted to object.
     */
    protected abstract void initSimpleInstance(HashMap<String, String> data);

    /**
     *
     * This method will be called when you want to convert the strings into <b>base64</b> coded image.
     *
     * @param data The provided data map will be converted to object.
     */
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
