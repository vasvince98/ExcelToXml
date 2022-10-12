package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.AuxClasses.AbstractAnswer;
import com.edti.exceltoxml.Models.AuxClasses.Answer;
import com.edti.exceltoxml.Models.AuxClasses.SubQuestion;
import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.QuestionTypes.Matching;
import com.edti.exceltoxml.Models.QuestionTypes.RealQuestion;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class MatchingQuestionProvider extends QuestionObjectProvider {
    @Override
    protected void initFieldNumbers() {

    }

    @Override
    protected ArrayList<AbstractAnswer> getAnswerObjectList(CellRangeAddress addressRange) {
        ArrayList<AbstractAnswer> list = new ArrayList<>();
        list.add(new SubQuestion());
        return list;
    }

    @Override
    protected RealQuestion getQuestion(HashMap<String, String> dataMap, QType type) {
        return (Matching) QuestionFactory.getQuestion(type, dataMap);
    }
}
