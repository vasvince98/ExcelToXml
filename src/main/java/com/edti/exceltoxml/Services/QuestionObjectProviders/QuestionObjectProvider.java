package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class QuestionObjectProvider {

    protected Sheet sheet;
    protected int numberOfFields;
    protected int firstRow = 5;
    protected int dataColumn = 1;
    protected int answerFields = 3;


    //todo: override equals method of category
    public abstract Map<Category, List<RealQuestion>> objectListFromSheet(Sheet sheet);

    protected HashMap<Integer, HashMap<String, String>> createQuestionMap() {
        int i = 0;

        sheet.setActiveCell(new CellAddress("B5"));
        System.out.println(sheet.getPhysicalNumberOfRows());

        while (i < 2) {
            HashMap<String, String> questionMap = new HashMap<>();
            for (int j = 0; j < numberOfFields; j++){
                CellRangeAddress range = new CellRangeAddress(firstRow, numberOfFields, dataColumn, dataColumn);
                if (isQuestion(new CellAddress(firstRow, dataColumn))) {
                    range.forEach((a) -> questionMap.put(getMapKeyFromAddress(a), getMapValueFromAddress(a)));
                } else {
                    System.out.println("This is a category");
                }
            }
            firstRow += numberOfFields;
            questionMap.forEach((k, v) -> System.out.println("Key: " + k + "\nValue: " + v));
            i++;
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
        this.numberOfFields = numberOfFields + 4;
    }

    private String getMapKeyFromAddress(CellAddress address) {
        return sheet.getRow(address.getRow()).getCell(address.getColumn()).getStringCellValue();
    }

    private String getMapValueFromAddress(CellAddress address) {
        return sheet.getRow(address.getRow()).getCell(address.getColumn() + 1).toString();
    }

    private boolean isQuestion(CellAddress address) {
        return !sheet.getRow(address.getRow()).getCell(address.getColumn() - 1).toString().equals("Kategória");
    }


}
