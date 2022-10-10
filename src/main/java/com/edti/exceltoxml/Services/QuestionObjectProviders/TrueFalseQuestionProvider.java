package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.Enums.QType;
import com.edti.exceltoxml.Models.Q.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Multichoice;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Truefalse;
import org.apache.poi.ss.usermodel.Sheet;
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
        createQuestionListWithCategoryName();

        initFieldNumbers();
        createAnswerList();

        questionListWithCategoryName.forEach(((cat, questionMaps) -> {
            List<RealQuestion> questionList = new ArrayList<>();
            questionMaps.forEach((question) -> {
                Truefalse currentQuestion = getQuestion(question);
                String currentIdNumber = currentQuestion.getIdnumber();
                currentQuestion.setAnswer(createAnswerList().get(currentIdNumber));

                questionList.add(currentQuestion);
            });
            resultMap.put(cat, questionList);
        }));
        return resultMap;
    }

    private Truefalse getQuestion(HashMap<String, String> dataMap) {
        return (Truefalse) QuestionFactory.getQuestion(QType.multichoice, dataMap);
    }

    @Override
    protected void initFieldNumbers() {
        this.setNumberOfQuestionFields(6);
        this.setAnswerRows(3);
        this.setAnswerColumns(3);
    }


}
