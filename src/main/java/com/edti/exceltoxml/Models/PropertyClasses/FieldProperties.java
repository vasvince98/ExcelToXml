package com.edti.exceltoxml.Models.PropertyClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:fields.properties")
@Component
public class FieldProperties {
    //region Fields
    private String questionName;
    private String questionText;
    private String generalFeedback;
    private String defaultGrade;
    private String penalty;
    private String hidden;
    private String single;
    private String shuffleAnswers;
    private String answerNumbering;
    private String showStandardInstruction;
    private String idNumber;
    private String correctFeedback;
    private String partiallyCorrectFeedback;
    private String incorrectFeedback;
    private String showNumCorrect;
    //endregion


    //region Constructors


    @Autowired
    public FieldProperties(@Value("${name}") String questionName,
                           @Value("${questiontext}")String questionText,
                           @Value("${generalfeedback}")String generalFeedback,
                           @Value("${defaultgrade}")String defaultGrade,
                           @Value("${penalty}")String penalty,
                           @Value("${hidden}")String hidden,
                           @Value("${single}")String single,
                           @Value("${shuffleanswers}")String shuffleAnswers,
                           @Value("${answernumbering}")String answerNumbering,
                           @Value("${showstandardinstruction}")String showStandardInstruction,
                           @Value("${idnumber}")String idNumber,
                           @Value("${correctfeedback}") String correctFeedback,
                           @Value("${partiallycorrectfeedback}") String partiallyCorrectFeedback,
                           @Value("${incorrectfeedback}") String incorrectFeedback,
                           @Value("${shownumcorrect}") String showNumCorrect) {
        this.questionName = questionName;
        this.questionText = questionText;
        this.generalFeedback = generalFeedback;
        this.defaultGrade = defaultGrade;
        this.penalty = penalty;
        this.hidden = hidden;
        this.single = single;
        this.shuffleAnswers = shuffleAnswers;
        this.answerNumbering = answerNumbering;
        this.showStandardInstruction = showStandardInstruction;
        this.idNumber = idNumber;
        this.correctFeedback = correctFeedback;
        this.partiallyCorrectFeedback = partiallyCorrectFeedback;
        this.incorrectFeedback = incorrectFeedback;
        this.showNumCorrect = showNumCorrect;
    }

    //endregion


    //region Getters and setters


    public String getQuestionName() {
        return questionName;
    }


    @Value("${name}")
    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getGeneralFeedback() {
        return generalFeedback;
    }

    public void setGeneralFeedback(String generalFeedback) {
        this.generalFeedback = generalFeedback;
    }

    public String getDefaultGrade() {
        return defaultGrade;
    }

    public void setDefaultGrade(String defaultGrade) {
        this.defaultGrade = defaultGrade;
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

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getShuffleAnswers() {
        return shuffleAnswers;
    }

    public void setShuffleAnswers(String shuffleAnswers) {
        this.shuffleAnswers = shuffleAnswers;
    }

    public String getAnswerNumbering() {
        return answerNumbering;
    }

    public void setAnswerNumbering(String answerNumbering) {
        this.answerNumbering = answerNumbering;
    }

    public String getShowStandardInstruction() {
        return showStandardInstruction;
    }

    public void setShowStandardInstruction(String showStandardInstruction) {
        this.showStandardInstruction = showStandardInstruction;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCorrectFeedback() {
        return correctFeedback;
    }

    public void setCorrectFeedback(String correctFeedback) {
        this.correctFeedback = correctFeedback;
    }

    public String getPartiallyCorrectFeedback() {
        return partiallyCorrectFeedback;
    }

    public void setPartiallyCorrectFeedback(String partiallyCorrectFeedback) {
        this.partiallyCorrectFeedback = partiallyCorrectFeedback;
    }

    public String getIncorrectFeedback() {
        return incorrectFeedback;
    }

    public void setIncorrectFeedback(String incorrectFeedback) {
        this.incorrectFeedback = incorrectFeedback;
    }

    public String getShowNumCorrect() {
        return showNumCorrect;
    }

    public void setShowNumCorrect(String showNumCorrect) {
        this.showNumCorrect = showNumCorrect;
    }

    //endregion

}
