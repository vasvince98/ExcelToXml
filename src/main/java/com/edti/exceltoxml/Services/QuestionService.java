package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Exceptions.MissingDataException;
import com.edti.exceltoxml.Exceptions.MissingHeaderException;
import com.edti.exceltoxml.Exceptions.NullAnswerException;
import com.edti.exceltoxml.Models.Category.Category;
import com.edti.exceltoxml.Models.Category.Info;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import com.edti.exceltoxml.Models.Question.*;
import com.edti.exceltoxml.Models.Question.Name;
import com.edti.exceltoxml.Models.Quiz;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IQuestionService;
import com.edti.exceltoxml.Services.Interfaces.IStateService;
import com.edti.exceltoxml.Services.QuestionObjectProviders.MultichoiceQuestionProvider;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.marshaller.NoEscapeHandler;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.io.File;
import java.util.*;

@Service
public class QuestionService implements IQuestionService {

    private List<SubQuestion> subQuestionList;
    private List<Dragbox> dragboxList;
    private List<Answer> answerList;

    private String questionType;

    private String finalXml;


    private final IImageService imageService;
    private final IStateService stateService;

    @Autowired
    public QuestionService(IImageService imageService, IStateService stateService) {
        this.imageService = imageService;
        this.stateService = stateService;
    }



    @Override
    public String createXmlFromExcel(Workbook workbook) throws IOException {
        finalXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<quiz>\n";
        workbook.setActiveSheet(0);
        MultichoiceQuestionProvider multichoiceQuestionProvider = new MultichoiceQuestionProvider();
        Map<Cat, List<RealQuestion>> multichoiceMap = new HashMap<>();

        for (Sheet sheet : workbook) {
            switch (sheet.getSheetName()) {
                case "feleletválasztó" ->  multichoiceMap = multichoiceQuestionProvider.objectListFromSheet(sheet);
//                case "igazhamis" -> truefalseMap = truefalseQuestionProvider.objectListFromSheet(sheet);
                default -> System.out.println("Nincs még lekezelve");
            }

        }
        return createFinalXml(multichoiceMap);
    }

    @Override
    public String createXmlFromQuiz(Quiz quiz) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Quiz.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        CharacterEscapeHandler escapeHandler = NoEscapeHandler.theInstance;
        jaxbMarshaller.setProperty("com.sun.xml.bind.characterEscapeHandler", escapeHandler);

        StringWriter sw = new StringWriter();

        jaxbMarshaller.marshal(quiz, sw);

        return sw.toString();
    }


    private String createFinalXml(Map<Cat, List<RealQuestion>> map) {

        map.forEach(((cat, realQuestions) -> {
            try {
                finalXml = finalXml.concat(cat.getXmlForm());
                realQuestions.forEach((realQuestion -> {
                    try {
                        finalXml = finalXml.concat(realQuestion.getXmlForm());
                    } catch (JAXBException | FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }));
            } catch (JAXBException | FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }));
        finalXml = finalXml.concat("\n</quiz>");
        return finalXml;
    }

    private List<Answer> createAnswers(String questionType, String solution) {
        List<Answer> answers = new ArrayList<>();
        Answer firstAnswer = new Answer();
        Answer secondAnswer = new Answer();

        if (questionType.equals("igaz-hamis")) {

            firstAnswer.setFormat("moodle_auto_format");
            secondAnswer.setFormat("moodle_auto_format");

            firstAnswer.setFeedback(new Feedback("","html"));
            secondAnswer.setFeedback(new Feedback("","html"));

            if (solution.equals("igaz") || solution.equals("true")) {
                firstAnswer.setFraction("100");
                secondAnswer.setFraction("0");
            } else {
                firstAnswer.setFraction("0");
                secondAnswer.setFraction("100");
            }


            firstAnswer.setText("true");
            secondAnswer.setText("false");

            answers.add(firstAnswer);
            answers.add(secondAnswer);
        }

        return answers;
    }

    private void removeFirstRow(Sheet sheet) {
        try {
            sheet.removeRow(sheet.getRow(0));
            System.out.println("First row deleted!");
        } catch (Exception e) {
            throw new MissingHeaderException("Az első sorban szerepelnie kell a mező típusoknak!");
        }

    }

    private void createSubQuestion(int i, Row row) {
        SubQuestion subQuestion = new SubQuestion();

        subQuestion.setText(row.getCell(i).getStringCellValue());
        subQuestion.setAnswer(new Answer(row.getCell(i+1).getStringCellValue()));

        subQuestionList.add(subQuestion);

    }

    private Quiz createQuizFromXml(java.io.File xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Quiz.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();


        return (Quiz) unmarshaller.unmarshal(xml);
    }

    private String getStringFromHTML(String html) {
        return Jsoup.parse(html).text();
    }

    private void replaceQuestionAndAnswer(Question question) throws IOException {
        String questionText = getStringFromHTML(question.getQuestiontext().getText());

        question.setQuestiontext(new QuestionText("html", imageService.transformStringToBase64(questionText)));

        for (Answer answer : question.getAnswer()) {
            String answerText = getStringFromHTML(answer.getText());
            answer.setText(String.format("<img src=\"data:image/png;base64,%s\"/>",imageService.transformStringToBase64(answerText)));
        }
    }

    private Question createTrueFalse(Row row) throws IOException {
        int i = 0;
        Question question = new Question();
        for (Cell cell : row) {

            if (cell.getColumnIndex() != i) {
                throw new MissingDataException("Every cell must be filled!");
            }

            switch (i) {
                case 0 -> question.setType("truefalse");
                case 1 -> question.setName(new Name(cell.getStringCellValue()));
                case 2 -> question.setQuestiontext(new QuestionText("html",
                        imageService.transformStringToBase64(cell.getStringCellValue())));
                case 3 -> question.setDefaultgrade(cell.getNumericCellValue());
                case 4 -> question.setAnswer(createAnswers(this.questionType, cell.toString().toLowerCase()));
                default -> System.out.println("It's over Anakin!");
            }
            i++;
        }
        return question;
    }

    private Question createMatching(Row row) throws IOException {
        Question question = new Question();
        subQuestionList = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            switch (i) {
                case 0 -> question.setType("matching");
                case 1 -> question.setName(new Name(row.getCell(i).getStringCellValue()));
                case 2 -> question.setQuestiontext(new QuestionText("html",
                        imageService.transformStringToBase64(row.getCell(i).getStringCellValue())));
                case 3 -> question.setDefaultgrade(row.getCell(i).getNumericCellValue());
                case 4, 6, 8, 10, 12 -> createSubQuestion(i, row);
                default -> System.out.println("It's over Anakin!");
            }
        }
        question.setPenalty(0.33);
        question.setShuffleanswers(true);
        question.setCorrectfeedback(new CorrectFeedback("Válasza helyes", "html"));
        question.setPartiallycorrectfeedback(new PartiallyCorrectFeedback("Válasza részben helyes", "html"));
        question.setIncorrectfeedback(new IncorrectFeedback("Válasza helytelen", "html"));
        question.setSubquestion(subQuestionList);

        return question;
    }

    private Question createMultichoice(Row row) throws IOException {
        Question question = new Question();
        this.answerList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {

            switch (i) {
                case 0 -> question.setType("multichoice");
                case 1 -> question.setName(new Name(row.getCell(i).getStringCellValue()));
                case 2 -> question.setQuestiontext(new QuestionText("html",
                        imageService.transformStringToBase64(row.getCell(i).getStringCellValue())));
                case 3 -> question.setDefaultgrade(row.getCell(i).getNumericCellValue());
                case 5, 7, 9, 11 -> {
                    if (row.getCell(i+1).getNumericCellValue() == 0) {
                        try {
                            throw new NullAnswerException("Nem vont le pontot a rossz válaszért! Biztosan beküldi a tesztet?");
                        } catch (Exception e) {
                            //helo
                        } continue;
                    }
                    if (row.getCell(i) != null) {
                        answerList.add(new Answer(row.getCell(i).getStringCellValue(),
                                new Feedback("",
                                        "html"),
                                Double.toString(row.getCell(i+1).getNumericCellValue()), "html"));
                    }
                }
                default -> System.out.println("It's over Anakin!");
            }
        }
        question.setPenalty(0.33);
        question.setShuffleanswers(true);
        question.setCorrectfeedback(new CorrectFeedback("Válasza helyes", "html"));
        question.setPartiallycorrectfeedback(new PartiallyCorrectFeedback("Válasza részben helyes", "html"));
        question.setIncorrectfeedback(new IncorrectFeedback("Válasza helytelen", "html"));
        question.setAnswer(answerList);

        return question;

    }

    private Question createDdwtos(Row row) {
        Question question = new Question();
        this.dragboxList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {

            switch (i) {
                case 0 -> question.setType("ddwtos");
                case 1 -> question.setName(new Name(row.getCell(i).getStringCellValue()));
                case 2 -> question.setQuestiontext(new QuestionText("html", row.getCell(i).getStringCellValue()));
                case 3 -> question.setDefaultgrade(row.getCell(i).getNumericCellValue());
                case 4, 5, 6, 7, 8 -> {
                    if (row.getCell(i) != null) {
                        dragboxList.add(new Dragbox(row.getCell(i).getStringCellValue()));
                    }

                }
                default -> System.out.println("It's over Anakin!");
            }
        }
        question.setPenalty(0.33);
        question.setShuffleanswers(true);
        question.setCorrectfeedback(new CorrectFeedback("Válasza helyes", "html"));
        question.setPartiallycorrectfeedback(new PartiallyCorrectFeedback("Válasza részben helyes", "html"));
        question.setIncorrectfeedback(new IncorrectFeedback("Válasza helytelen", "html"));
        question.setDragbox(dragboxList);

        return question;
    }
}
