package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlType(propOrder = {"name", "questionText", "generalFeedback", "defaultGrade", "penalty",
"hidden", "idNumber", "single", "shuffleAnswers", "answerNumbering", "showStandardInstruction", "correctFeedback",
"partiallyCorrectFeedback", "incorrectFeedback", "answers"})
public class Question {


    private String type;

    private Name name;
    private QuestionText questionText;
    private GeneralFeedback generalFeedback;
    private double defaultGrade;
    private double penalty;
    private int hidden;
    private String idNumber;
    private boolean single;
    private boolean shuffleAnswers;
    private String answerNumbering;
    private int showStandardInstruction;
    private CorrectFeedback correctFeedback;
    private PartiallyCorrectFeedback partiallyCorrectFeedback;
    private IncorrectFeedback incorrectFeedback;
    private List<Answer> answers;

    public Question() {}

    public Question(String type, Name name, QuestionText questionText, GeneralFeedback generalFeedback,
                    double defaultGrade, double penalty, int hidden, String idNumber,
                    boolean single, boolean shuffleAnswers, String answerNumbering,
                    int showStandardInstruction, CorrectFeedback correctFeedback,
                    PartiallyCorrectFeedback partiallyCorrectFeedback,
                    IncorrectFeedback incorrectFeedback) {
        this.type = type;
        this.name = name;
        this.questionText = questionText;
        this.generalFeedback = generalFeedback;
        this.defaultGrade = defaultGrade;
        this.penalty = penalty;
        this.hidden = hidden;
        this.idNumber = idNumber;
        this.single = single;
        this.shuffleAnswers = shuffleAnswers;
        this.answerNumbering = answerNumbering;
        this.showStandardInstruction = showStandardInstruction;
        this.correctFeedback = correctFeedback;
        this.partiallyCorrectFeedback = partiallyCorrectFeedback;
        this.incorrectFeedback = incorrectFeedback;
        this.answers = new ArrayList<>();
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public QuestionText getQuestionText() {
        return questionText;
    }

    public void setQuestionText(QuestionText questionText) {
        this.questionText = questionText;
    }

    public GeneralFeedback getGeneralFeedback() {
        return generalFeedback;
    }

    public void setGeneralFeedback(GeneralFeedback generalFeedback) {
        this.generalFeedback = generalFeedback;
    }

    public double getDefaultGrade() {
        return defaultGrade;
    }

    public void setDefaultGrade(double defaultGrade) {
        this.defaultGrade = defaultGrade;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public boolean isShuffleAnswers() {
        return shuffleAnswers;
    }

    public void setShuffleAnswers(boolean shuffleAnswers) {
        this.shuffleAnswers = shuffleAnswers;
    }

    public String getAnswerNumbering() {
        return answerNumbering;
    }

    public void setAnswerNumbering(String answerNumbering) {
        this.answerNumbering = answerNumbering;
    }

    public int getShowStandardInstruction() {
        return showStandardInstruction;
    }

    public void setShowStandardInstruction(int showStandardInstruction) {
        this.showStandardInstruction = showStandardInstruction;
    }

    public CorrectFeedback getCorrectFeedback() {
        return correctFeedback;
    }

    public void setCorrectFeedback(CorrectFeedback correctFeedback) {
        this.correctFeedback = correctFeedback;
    }

    public PartiallyCorrectFeedback getPartiallyCorrectFeedback() {
        return partiallyCorrectFeedback;
    }

    public void setPartiallyCorrectFeedback(PartiallyCorrectFeedback partiallyCorrectFeedback) {
        this.partiallyCorrectFeedback = partiallyCorrectFeedback;
    }

    public IncorrectFeedback getIncorrectFeedback() {
        return incorrectFeedback;
    }

    public void setIncorrectFeedback(IncorrectFeedback incorrectFeedback) {
        this.incorrectFeedback = incorrectFeedback;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name=" + name +
                ", questionText=" + questionText +
                ", generalFeedback=" + generalFeedback +
                ", defaultGrade=" + defaultGrade +
                ", penalty=" + penalty +
                ", hidden=" + hidden +
                ", idNumber='" + idNumber + '\'' +
                ", single=" + single +
                ", shuffleAnswers=" + shuffleAnswers +
                ", answerNumbering='" + answerNumbering + '\'' +
                ", showStandardInstruction=" + showStandardInstruction +
                ", correctFeedback=" + correctFeedback +
                ", partiallyCorrectFeedback=" + partiallyCorrectFeedback +
                ", incorrectFeedback=" + incorrectFeedback +
                ", answers=" + answers +
                '}';
    }
}
