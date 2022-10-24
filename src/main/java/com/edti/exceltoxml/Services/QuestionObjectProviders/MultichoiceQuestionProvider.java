package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.AuxClasses.AbstractAnswer;
import com.edti.exceltoxml.Models.AuxClasses.Answer;
import com.edti.exceltoxml.Models.AuxClasses.Feedback;
import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.QuestionTypes.Multichoice;
import com.edti.exceltoxml.Models.QuestionTypes.RealQuestion;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MultichoiceQuestionProvider extends QuestionObjectProvider {

    @Override
    protected RealQuestion getQuestion(HashMap<String, String> dataMap, QType type) throws IOException {
        return (Multichoice) QuestionFactory.getQuestion(type, dataMap);
    }

    @Override
    protected void initFieldNumbers() {
        this.setNumberOfQuestionFields(11);
        this.setAnswerRows(3);
        this.setAnswerColumns(6);
    }

    @Override
    protected ArrayList<AbstractAnswer> getAnswerObjectList(CellRangeAddress addressRange) {
        ArrayList<AbstractAnswer> answerList = new ArrayList<>();
        addressRange.forEach((r) -> {
            Answer currentAnswer = new Answer();
            currentAnswer.setImageText(sheet.getRow(r.getRow()).getCell(r.getColumn()).toString());
            currentAnswer.setFraction(sheet.getRow(r.getRow() + 1).getCell(r.getColumn()).toString());
            currentAnswer.setFeedback(new Feedback(sheet.getRow(r.getRow() + 2).getCell(r.getColumn()).toString()));


            answerList.add(currentAnswer);

        });
        return answerList;
    }
}
