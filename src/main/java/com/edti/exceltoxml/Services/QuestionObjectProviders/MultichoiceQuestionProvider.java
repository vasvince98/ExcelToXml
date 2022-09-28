package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Multichoice;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MultichoiceQuestionProvider extends QuestionObjectProvider {

    private int categoryNumber;

    @Override
    public Map<Category, List<RealQuestion>> objectListFromSheet(Sheet sheet) {
        this.sheet = sheet;
        setNumberOfFields(11);
        createQuestionMap();
        return null;
    }

    @Override
    protected HashMap<String, String> getQuestionMap() {
        return null;
    }

    @Override
    protected List<Answer> createAnswerList() {
        return null;
    }
}
