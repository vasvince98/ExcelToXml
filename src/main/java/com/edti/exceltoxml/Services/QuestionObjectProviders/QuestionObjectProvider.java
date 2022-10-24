package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.AuxClasses.AbstractAnswer;
import com.edti.exceltoxml.Models.AuxClasses.Answer;
import com.edti.exceltoxml.Models.AuxClasses.Category;
import com.edti.exceltoxml.Models.AuxClasses.Info;
import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.PropertyClasses.FieldProperties;
import com.edti.exceltoxml.Models.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.QuestionTypes.RealQuestion;
import com.edti.exceltoxml.Services.StateService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class QuestionObjectProvider {

    //region Fields
    protected Sheet sheet;
    protected int numberOfFields;
    protected int firstRow;
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

    private int questionCounter;
    private int categoryCounter;

    //endregion

    //region Getters and Setters
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


    /**
     *
     * @param sheet Current sheet of the Excel file. There will be only one type of question in a specific sheet.
     * @param type Type of the question (ENUM)
     * @return a map, where the key is a category object, and the value is a list of question within the key category
     */
    public Map<Cat, List<RealQuestion>> objectListFromSheet(Sheet sheet, QType type) {
        HashMap<Cat, List<RealQuestion>> resultMap = new HashMap<>();

        this.sheet = sheet;
        initFieldNumbers();
        createQuestionListWithCategoryName();

        initFieldNumbers();
        createAnswerMapWithID();



        questionListWithCategoryName.forEach(((cat, questionMaps) -> {
            List<RealQuestion> questionList = new ArrayList<>();
            questionMaps.forEach((question) -> {
                RealQuestion currentQuestion;
                try {
                    currentQuestion = getQuestion(question, type);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String currentIdNumber = currentQuestion.getIdnumber();

                currentQuestion.setAnswer(createAnswerMapWithID().get(currentIdNumber), type);


                questionList.add(currentQuestion);
            });
            resultMap.put(cat, questionList);
        }));
        return resultMap;
    }

    /**
     *
     * @return a map with a key: Category name, and a value: A list with a question hash map, where the
     *          key value pairs represents the question fields
     */
    protected HashMap<Cat, List<HashMap<String, String>>> createQuestionListWithCategoryName() {
        HashMap<Cat, List<HashMap<String, String>>> resultMap = new HashMap<>();
        int i = 0;
        firstRow = 0;
        this.categoryCounter = 0;
        this.questionCounter = 0;

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

    /**
     *
     * @return a map, where the key is an idNumber with two numbers. The first number is the category number, the second is the question number.
     *              This is important to know, because in the future will be paired the question and the answer based on this idNumber.
     */
    protected HashMap<String, ArrayList<AbstractAnswer>> createAnswerMapWithID() {
        this.firstRow = 0;
        this.categoryCounter = 0;
        this.questionCounter = 0;
        int i = 0;

        HashMap<String, ArrayList<AbstractAnswer>> answerMapWithId = new HashMap<>();

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

        return answerMapWithId;
    }

    /**
     *
     * Set the field numbers based on the Excel file's rows.
     * Fields needed to be set:
     *      <li><b>setNumberOfQuestionFields</b> -> All the fields in the Excel, except the answer's fields.</li>
     *      <li><b>setAnswerRows</b> -> Number of the answer rows</li>
     *      <li><b>setAnswerColumns</b> -> Number of the answer columns. The option fields are included.</li>
     */
    protected abstract void initFieldNumbers();

    /**
     *
     * @param addressRange Contains all the cell address of the first answer row.
     * @return List of all the answers of a one specific question.
     */
    protected abstract ArrayList<AbstractAnswer> getAnswerObjectList(CellRangeAddress addressRange);

    /**
     *
     * After overriding the method you have to cast the return value to
     * the class of the question!
     *
     * @param dataMap Map, where the key is a field from the Excel, and a value is the questions value
     * @param type type of the question (ENUM)
     * @return a real question object based on the dataMap parameter
     */
    protected abstract RealQuestion getQuestion(HashMap<String, String> dataMap, QType type) throws IOException;




    private String getMapKeyFromAddress(CellAddress address) {
        try {
            return sheet.getRow(address.getRow()).getCell(address.getColumn()).getStringCellValue();
        } catch (Exception e) {
            System.out.println("Empty cell at: " + address);        //todo: replace with log
            return null;
        }

    }

    private String getMapValueFromAddress(CellAddress address) {
        try {
            return sheet.getRow(address.getRow()).getCell(address.getColumn() + 1).toString();
        } catch (Exception e) {
            System.out.println("Empty cell at: " + address);        //todo: replace with log
            return null;
        }
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


}
