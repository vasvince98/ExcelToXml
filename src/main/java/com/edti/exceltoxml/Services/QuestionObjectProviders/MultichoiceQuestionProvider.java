package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;
import com.edti.exceltoxml.Models.Q.AuxClasses.Info;
import com.edti.exceltoxml.Models.Q.Enums.QType;
import com.edti.exceltoxml.Models.Q.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Multichoice;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MultichoiceQuestionProvider extends QuestionObjectProvider {

    @Override
    public Map<Cat, List<RealQuestion>> objectListFromSheet(Sheet sheet) {
        HashMap<Cat, List<RealQuestion>> resultMap = new HashMap<>();

        this.sheet = sheet;
        setNumberOfFields(11);
        createQuestionListWithCategoryName();
//        questionList.add(getQuestion());

        questionListWithCategoryName.forEach(((cat, questionMaps) -> {
            List<RealQuestion> questionList = new ArrayList<>();
            questionMaps.forEach((question) -> questionList.add(getQuestion(question)));
            resultMap.put(cat, questionList);
        }));
        resultMap.forEach(((cat, realQuestions) -> {
            System.out.println(cat);
            realQuestions.forEach((System.out::println));
        }));
        return resultMap;
    }

    @Override
    protected HashMap<String, String> getQuestionMap() {
        return null;
    }

    @Override
    protected List<Answer> createAnswerList() {
        return null;
    }

    private Multichoice getQuestion(HashMap<String, String> dataMap) {
        return (Multichoice) QuestionFactory.getQuestion(QType.multichoice, dataMap);
    }
}
