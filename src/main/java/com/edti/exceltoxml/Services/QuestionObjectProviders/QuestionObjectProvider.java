package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;
import com.edti.exceltoxml.Models.Q.AuxClasses.Feedback;
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

    protected int answerRows;
    protected int firstColumn = 2;
    protected int answerColumns;

    protected int categoryFields = 2;
    private Cat category;
    private List<HashMap<String, String>> questionList;
    protected HashMap<Cat, List<HashMap<String, String>>> questionListWithCategoryName;

    private HashMap<String, HashMap<String, String>> resultMap = new HashMap<>();

    private int questionCounter = 0;
    private int categoryCounter = 0;


    public abstract Map<Cat, List<RealQuestion>> objectListFromSheet(Sheet sheet);

    /**
     *
     * @return a map with a key: Category name, and a value: A list with a question hash map, where the
     *          key value pairs represents the question fields
     */
    protected HashMap<Cat, List<HashMap<String, String>>> createQuestionListWithCategoryName() {

        HashMap<Cat, List<HashMap<String, String>>> resultMap = new HashMap<>();

        int i = 0;


        lastRow = numberOfFields + firstRow;

        while (i < sheet.getPhysicalNumberOfRows()) {
            CellRangeAddress range = new CellRangeAddress(firstRow, lastRow - 1, dataColumn, dataColumn);
            if (isQuestion(new CellAddress(firstRow, dataColumn))) {
                HashMap<String, String> questionMap = new HashMap<>();
                questionCounter++;
                range.forEach((a) -> questionMap.put(getMapKeyFromAddress(a), getMapValueFromAddress(a)));
                questionMap.put("id", categoryCounter + ", " + questionCounter);
                questionList.add(questionMap);
                i += numberOfFields + answerRows;
                nextQuestion();
            } else {
                if (firstRow != 0) {
                    resultMap.put(this.category, questionList);
                }
                this.questionList = new ArrayList<>();
                questionCounter = 0;
                categoryCounter++;
                setCategory(new CellAddress(firstRow, dataColumn));
                i += categoryFields;
            }
        }
        resultMap.put(this.category, questionList);
        this.questionListWithCategoryName = resultMap;
        return resultMap;
    }

    protected HashMap<String, ArrayList<Answer>> createAnswerList() {
        this.firstRow = 0;
        this.categoryCounter = 0;
        this.questionCounter = 0;
        int i = 0;

        HashMap<String, ArrayList<Answer>> answerMapWithId = new HashMap<>();

        while (i < sheet.getPhysicalNumberOfRows()) {

            CellRangeAddress range;

            if (isQuestion(new CellAddress(firstRow, dataColumn))) {
                questionCounter++;
                firstRow += numberOfFields;
                i += numberOfFields;

                range = new CellRangeAddress(firstRow, firstRow, firstColumn, answerColumns);
                answerMapWithId.put(categoryCounter + ", " + questionCounter, getAnswerObjectList(range));


                i += answerRows;
                firstRow += answerRows;
            } else {
                questionCounter = 0;
                categoryCounter++;
                i += categoryFields;
                firstRow += categoryFields;
            }
        }

        answerMapWithId.forEach((id, ans) -> System.out.println(id));
        return answerMapWithId;
    }

    protected abstract void initFieldNumbers();


    protected Sheet getSheet() {
        return this.sheet;
    }

    protected void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public int getNumberOfFields() {
        return numberOfFields;
    }

    public void setNumberOfQuestionFields(int numberOfFields) {
        this.numberOfFields = numberOfFields;
    }

    private String getMapKeyFromAddress(CellAddress address) {
        return sheet.getRow(address.getRow()).getCell(address.getColumn()).getStringCellValue();
    }

    private String getMapValueFromAddress(CellAddress address) {
        return sheet.getRow(address.getRow()).getCell(address.getColumn() + 1).toString();
    }

    private ArrayList<Answer> getAnswerObjectList(CellRangeAddress addressRange) {
        ArrayList<Answer> answerList = new ArrayList<>();
        addressRange.forEach((r) -> {
            Answer currentAnswer = new Answer();
            currentAnswer.setText(sheet.getRow(r.getRow()).getCell(r.getColumn()).toString());
            currentAnswer.setFraction(sheet.getRow(r.getRow() + 1).getCell(r.getColumn()).toString());
            currentAnswer.setFeedback(new Feedback(sheet.getRow(r.getRow() + 2).getCell(r.getColumn()).toString()));


            answerList.add(currentAnswer);

        });
        return answerList;
    }

    private boolean isQuestion(CellAddress address) {
        try {
            return !sheet.getRow(address.getRow()).getCell(address.getColumn() - 1).toString().equals("Kategória");
        } catch (Exception e) {
            return true;
        }

    }

    private void nextQuestion() {
        firstRow += numberOfFields + answerRows;
        lastRow += numberOfFields + answerRows;
    }

    private void setCategory(CellAddress address) {
        this.category = new Cat();
        this.category.setCategory(new Category(sheet.getRow(address.getRow()).getCell(address.getColumn() + 1).toString()));
        this.category.setInfo(new Info(sheet.getRow(address.getRow() + 1).getCell(address.getColumn() + 1).toString()));
        this.category.setType("category");
        firstRow += categoryFields;
        lastRow += categoryFields;
    }

    //region Getters and setters
    public int getAnswerColumns() {
        return answerColumns;
    }

    public void setAnswerColumns(int answerColumns) {
        this.answerColumns = answerColumns;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getAnswerRows() {
        return answerRows;
    }

    public void setAnswerRows(int answerRows) {
        this.answerRows = answerRows;
    }

    //endregion


}
