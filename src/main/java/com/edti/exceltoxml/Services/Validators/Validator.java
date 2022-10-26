package com.edti.exceltoxml.Services.Validators;

public abstract class Validator implements IValidator {

    protected boolean isQuestionTextValid(String text) {
        return text != null && !text.equals("");
    }

    protected boolean isDefaultGradeValid(String grade) {
        return grade != null && !(Integer.parseInt(grade) <= 0);
    }


}
