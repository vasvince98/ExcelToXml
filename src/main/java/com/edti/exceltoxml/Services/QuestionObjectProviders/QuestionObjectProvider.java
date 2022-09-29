package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class QuestionObjectProvider {

    protected Sheet sheet;
    protected int numberOfFields;
    protected int firstRow = 2;
    protected int lastRow;
    protected int dataColumn = 1;
    protected int answerFields = 3;
    protected int categoryFields = 2;


    //todo: override equals method of category
    public abstract Map<Category, List<RealQuestion>> objectListFromSheet(Sheet sheet);

    protected HashMap<Integer, HashMap<String, String>> createQuestionMap() {
        int i = 2;
        int questionCounter = 0;

        lastRow = numberOfFields + firstRow;

        while (i < sheet.getPhysicalNumberOfRows()) {
            questionCounter++;
            System.out.println("Question: " + questionCounter);
            HashMap<String, String> questionMap = new HashMap<>();
            CellRangeAddress range = new CellRangeAddress(firstRow, lastRow - 1, dataColumn, dataColumn);
            System.out.println("First row: " + firstRow);
            System.out.println("Current row:" + i);
            if (isQuestion(new CellAddress(firstRow, dataColumn))) {
                range.forEach((a) -> questionMap.put(getMapKeyFromAddress(a), getMapValueFromAddress(a)));
                i += numberOfFields + answerFields;
                nextQuestion();
            } else {
                System.out.println("This is a category");
                questionCounter = 0;
                skipCategory();
                i += categoryFields;
            }
            questionMap.forEach((k, v) -> System.out.println("Key: " + k + "\nValue: " + v));

        }
        return null;
    }

    protected abstract HashMap<String, String> getQuestionMap();

    protected abstract List<Answer> createAnswerList();


    protected Sheet getSheet() {
        return this.sheet;
    }

    protected void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public int getNumberOfFields() {
        return numberOfFields;
    }

    public void setNumberOfFields(int numberOfFields) {
        this.numberOfFields = numberOfFields;
    }

    private String getMapKeyFromAddress(CellAddress address) {
        return sheet.getRow(address.getRow()).getCell(address.getColumn()).getStringCellValue();
    }

    private String getMapValueFromAddress(CellAddress address) {
        return sheet.getRow(address.getRow()).getCell(address.getColumn() + 1).toString();
    }

    private boolean isQuestion(CellAddress address) {
        try {
            return !sheet.getRow(address.getRow()).getCell(address.getColumn() - 1).toString().equals("Kategória");
        } catch (Exception e) {
            return true;
        }

    }

    private void nextQuestion() {
        firstRow += numberOfFields + answerFields;
        lastRow += numberOfFields + answerFields;
    }

    private void skipCategory() {
        firstRow += categoryFields;
        lastRow += categoryFields;
    }


}
