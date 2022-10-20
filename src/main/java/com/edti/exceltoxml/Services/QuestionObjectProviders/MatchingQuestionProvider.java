package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.AuxClasses.AbstractAnswer;
import com.edti.exceltoxml.Models.AuxClasses.Answer;
import com.edti.exceltoxml.Models.AuxClasses.Subquestion;
import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.QuestionTypes.Matching;
import com.edti.exceltoxml.Models.QuestionTypes.RealQuestion;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MatchingQuestionProvider extends QuestionObjectProvider {
    @Override
    protected void initFieldNumbers() {
        this.setNumberOfQuestionFields(10);
        this.setAnswerRows(2);
        this.setAnswerColumns(8);
    }

    @Override
    protected ArrayList<AbstractAnswer> getAnswerObjectList(CellRangeAddress addressRange) {
        ArrayList<AbstractAnswer> answerList = new ArrayList<>();
        addressRange.forEach((r) -> {
            Subquestion currentSubquestion = new Subquestion();
            try {
                currentSubquestion.setText(sheet.getRow(r.getRow()).getCell(r.getColumn()).toString());
            } catch (Exception e) {
                System.out.println("Empty cell");
            }

            try {
                currentSubquestion.setAnswer(new Answer(sheet.getRow(r.getRow() + 1).getCell(r.getColumn()).toString()));
            } catch (Exception e) {
                System.out.println("Empty cell");
            }

            currentSubquestion.setFormat("html");


            answerList.add(currentSubquestion);
        });

        return answerList;
    }

    @Override
    protected RealQuestion getQuestion(HashMap<String, String> dataMap, QType type) throws IOException {
        return (Matching) QuestionFactory.getQuestion(type, dataMap);
    }
}
