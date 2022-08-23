package com.edti.exceltoxml.Models;


import com.edti.exceltoxml.Models.Question.Question;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"category", "question"})
public class Quiz {
    private List<QuestionCategory> category;
    private List<Question> question;

    public Quiz() {}

    public Quiz(List<QuestionCategory> category, List<Question> question) {
        this.category = new ArrayList<>();
        this.question = new ArrayList<>();
    }

    public List<QuestionCategory> getCategory() {
        return category;
    }

    public void setCategory(List<QuestionCategory> category) {
        this.category = category;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionBank{" +
                "categories=" + category +
                ", questions=" + question +
                '}';
    }
}
