package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Feedback;
import com.edti.exceltoxml.Models.Q.Enums.QType;
import com.edti.exceltoxml.Models.Q.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Truefalse;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrueFalseQuestionProvider extends QuestionObjectProvider {
    @Override
    public Map<Cat, List<RealQuestion>> objectListFromSheet(Sheet sheet) {
        HashMap<Cat, List<RealQuestion>> resultMap = new HashMap<>();

        this.sheet = sheet;
        initFieldNumbers();
        createQuestionListWithCategoryName(QType.truefalse);

        initFieldNumbers();
        createAnswerMapWithID();

        questionListWithCategoryName.forEach(((cat, questionMaps) -> {
            List<RealQuestion> questionList = new ArrayList<>();
            questionMaps.forEach((question) -> {
                RealQuestion currentQuestion = getQuestion(question);

                String currentIdNumber = currentQuestion.getIdnumber();

                currentQuestion.setAnswer(createAnswerMapWithID().get(currentIdNumber));


                questionList.add(currentQuestion);
            });
            resultMap.put(cat, questionList);
        }));
        return resultMap;
    }

    private Truefalse getQuestion(HashMap<String, String> dataMap) {
        return (Truefalse) QuestionFactory.getQuestion(QType.truefalse, dataMap);
    }

    @Override
    protected void initFieldNumbers() {
        this.setNumberOfQuestionFields(6);
        this.setAnswerRows(3);
        this.setAnswerColumns(3);
    }

    @Override
    protected ArrayList<Answer> getAnswerObjectList(CellRangeAddress addressRange) {
        ArrayList<Answer> answerList = new ArrayList<>();
        addressRange.forEach((r) -> {
            Answer currentAnswer = new Answer();
            currentAnswer.setText(sheet.getRow(r.getRow()).getCell(r.getColumn()).toString().toLowerCase());
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
