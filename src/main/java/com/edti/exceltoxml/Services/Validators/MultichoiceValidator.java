package com.edti.exceltoxml.Services.Validators;

import com.edti.exceltoxml.Exceptions.IncorrectFieldException;
import com.edti.exceltoxml.Models.QuestionTypes.RealQuestion;

public class MultichoiceValidator extends Validator {

    @Override
    public void validate(RealQuestion realQuestion) {
        System.out.println(realQuestion.getQuestiontext().getText());

    }

    private boolean isShuffleAnswerValid(String shuffleAnswer) {
        return true;
    }

    private String isSingle(String single) {
        switch (single) {
            case "IGAZ", "igaz", "true", "TRUE", "igen", "IGEN" -> {
                return "true";
            }

            case "HAMIS", "hamis", "false", "FALSE", "NEM", "nem" -> {
                return "false";
            }

            default -> throw new IncorrectFieldException("The 'Egy válasz a jó?' parameter was incorrect, please check it!");
        }
    }

}
