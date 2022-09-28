package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Multichoice;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultichoiceQuestionProvider extends QuestionObjectProvider {

    private int categoryNumber;
    
    @Override
    public Map<Category, List<RealQuestion>> objectListFromSheet(Sheet sheet) {
        setSheet(sheet);
        for (Row row : getSheet()) {
            if (row.getCell(0).getStringCellValue().equals("Kategória")) {
                System.out.println("Ez egy kategória");
            }
        }
        return null;
    }

    @Override
    protected HashMap<String, String> createQuestionMap() {
        for (Row row : getSheet()) {
        }
        return null;
    }

    @Override
    protected List<Answer> createAnswerList() {
        return null;
    }
}
