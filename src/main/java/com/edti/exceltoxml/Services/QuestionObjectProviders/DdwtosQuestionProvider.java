package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.AuxClasses.AbstractAnswer;
import com.edti.exceltoxml.Models.AuxClasses.Answer;
import com.edti.exceltoxml.Models.AuxClasses.Dragbox;
import com.edti.exceltoxml.Models.AuxClasses.Subquestion;
import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.QuestionTypes.Ddwtos;
import com.edti.exceltoxml.Models.QuestionTypes.RealQuestion;
import com.edti.exceltoxml.Services.StateService;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DdwtosQuestionProvider extends QuestionObjectProvider {

    @Override
    protected void initFieldNumbers() {
        this.setNumberOfQuestionFields(10);
        this.setAnswerRows(3);
        this.setAnswerColumns(8);
    }

    @Override
    protected ArrayList<AbstractAnswer> getAnswerObjectList(CellRangeAddress addressRange) {
        ArrayList<AbstractAnswer> answerList = new ArrayList<>();
        addressRange.forEach((r) -> {
            Dragbox currentDragbox = new Dragbox();
            try {
                currentDragbox.setAnswerNumber(sheet.getRow(r.getRow()).getCell(r.getColumn()).toString());
            } catch (Exception e) {
                System.out.println("Empty cell at: " + r);          //todo: replace with log
            }

            try {
                currentDragbox.setText(sheet.getRow(r.getRow() + 1).getCell(r.getColumn()).toString());
            } catch (Exception e) {
                System.out.println("Empty cell at: " + r);          //todo: replace with log
            }

            try {
                currentDragbox.setGroup((int) Double.parseDouble(sheet.getRow(r.getRow() + 2).getCell(r.getColumn()).toString()));
            } catch (Exception e) {
                System.out.println("Empty cell at: " + r);          //todo: replace with log
            }


            answerList.add(currentDragbox);
        });

        return answerList;
    }

    @Override
    protected RealQuestion getQuestion(HashMap<String, String> dataMap, QType type) throws IOException {
        return (Ddwtos) QuestionFactory.getQuestion(type, dataMap);
    }
}
