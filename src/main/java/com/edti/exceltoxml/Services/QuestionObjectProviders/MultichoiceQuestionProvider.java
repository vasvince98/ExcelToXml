package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultichoiceQuestionProvider extends QuestionObjectProvider {

    
    @Override
    public Map<Category, List<RealQuestion>> objectListFromSheet(Sheet sheet) {
        return null;
    }

    @Override
    protected HashMap<String, String> createQuestionMap() {
        return null;
    }

    @Override
    protected List<Answer> createQuestionList() {
        return null;
    }
}
