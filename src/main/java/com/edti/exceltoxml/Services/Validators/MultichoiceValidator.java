package com.edti.exceltoxml.Services.Validators;

import com.edti.exceltoxml.Models.QuestionTypes.RealQuestion;

public class MultichoiceValidator extends Validator {

    @Override
    public void validate(RealQuestion realQuestion) {
        System.out.println(realQuestion.getQuestiontext().getText());

    }

    private boolean isShuffleAnswerValid(String shuffleAnswer) {
        return true;
    }
}
