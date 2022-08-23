package com.edti.exceltoxml.Models;


import com.edti.exceltoxml.Models.Question.Question;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Quiz {


//    private List<QuestionCategory> category;
    private List<Question> question;

    public Quiz() {}

    public Quiz(List<Question> question) {
        this.question = question;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

}
