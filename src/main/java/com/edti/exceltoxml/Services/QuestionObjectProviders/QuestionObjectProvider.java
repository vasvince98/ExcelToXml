package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;
import com.edti.exceltoxml.Models.Q.AuxClasses.Info;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class QuestionObjectProvider {

    protected Sheet sheet;
    protected int numberOfFields;
    protected int firstRow = 0;
    protected int lastRow;
    protected int dataColumn = 1;
    protected int answerFields = 3;
    protected int categoryFields = 2;
    private Cat category;
    private List<HashMap<String, String>> questionList;
    protected HashMap<Cat, List<HashMap<String, String>>> questionListWithCategoryName;

    private HashMap<String, HashMap<String, String>> resultMap = new HashMap<>();


    //todo: override equals method of category
    public abstract Map<Cat, List<RealQuestion>> objectListFromSheet(Sheet sheet);

    /**
     *
     * @return a map with a key: Category name, and a value: A list with a question hash map, where the
     *          key value pairs represents the question fields
     */
    protected HashMap<Cat, List<HashMap<String, String>>> createQuestionListWithCategoryName() {

        HashMap<Cat, List<HashMap<String, String>>> resultMap = new HashMap<>();

        int i = 0;
        int questionCounter = 0;

        lastRow = numberOfFields + firstRow;



        while (i < sheet.getPhysicalNumberOfRows()) {
            CellRangeAddress range = new CellRangeAddress(firstRow, lastRow - 1, dataColumn, dataColumn);
            if (isQuestion(new CellAddress(firstRow, dataColumn))) {
                HashMap<String, String> questionMap = new HashMap<>();
                questionCounter++;
                range.forEach((a) -> questionMap.put(getMapKeyFromAddress(a), getMapValueFromAddress(a)));
                questionList.add(questionMap);
                i += numberOfFields + answerFields;
                nextQuestion();
            } else {
                if (firstRow != 0) {
                    resultMap.put(this.category, questionList);
                }
                this.questionList = new ArrayList<>();
                questionCounter = 0;
                setCategory(new CellAddress(firstRow, dataColumn));
                i += categoryFields;
            }
        }
        resultMap.put(this.category, questionList);
        resultMap.forEach(((cat, hashMaps) -> {
            System.out.println(cat.getCategory());
            System.out.println(cat.getInfo());
            System.out.println(cat.getType());
            System.out.println(cat.getIdnumber());
        }));
        this.questionListWithCategoryName = resultMap;
        return resultMap;
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

    private void setCategory(CellAddress address) {
        this.category = new Cat();
        this.category.setCategory(new Category(sheet.getRow(address.getRow()).getCell(address.getColumn() + 1).toString()));
        this.category.setInfo(new Info(sheet.getRow(address.getRow() + 1).getCell(address.getColumn() + 1).toString()));
        this.category.setType("category");
        firstRow += categoryFields;
        lastRow += categoryFields;
    }


}
