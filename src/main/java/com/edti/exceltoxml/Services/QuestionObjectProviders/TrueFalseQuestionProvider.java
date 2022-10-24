package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.AuxClasses.AbstractAnswer;
import com.edti.exceltoxml.Models.AuxClasses.Answer;
import com.edti.exceltoxml.Models.AuxClasses.Feedback;
import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.QuestionTypes.RealQuestion;
import com.edti.exceltoxml.Models.QuestionTypes.Truefalse;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IStateService;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class TrueFalseQuestionProvider extends QuestionObjectProvider {

    public TrueFalseQuestionProvider(IImageService imageService, IStateService stateService) {
        super(imageService, stateService);
    }

    @Override
    protected RealQuestion getQuestion(HashMap<String, String> dataMap, QType type) throws IOException {
        return (Truefalse) QuestionFactory.getQuestion(type, dataMap);
    }

    @Override
    protected void initFieldNumbers() {
        this.setNumberOfQuestionFields(6);
        this.setAnswerRows(3);
        this.setAnswerColumns(3);
    }

    @Override
    protected ArrayList<AbstractAnswer> getAnswerObjectList(CellRangeAddress addressRange) {
        ArrayList<AbstractAnswer> answerList = new ArrayList<>();
        addressRange.forEach((r) -> {
            Answer currentAnswer = new Answer();
            currentAnswer.setSimpleText(sheet.getRow(r.getRow()).getCell(r.getColumn()).toString().toLowerCase());
            String answerPoint = sheet.getRow(r.getRow() + 1).getCell(r.getColumn()).toString();
            if (answerPoint.equals("0.0")) {
                currentAnswer.setFraction("0");
            } else {
                currentAnswer.setFraction("100");
            }

            currentAnswer.setFeedback(new Feedback(sheet.getRow(r.getRow() + 2).getCell(r.getColumn()).toString()));
            currentAnswer.setFormat("moodle_auto_format");


            answerList.add(currentAnswer);

        });
        return answerList;
    }


}
