package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Exceptions.MissingDataException;
import com.edti.exceltoxml.Exceptions.MissingHeaderException;
import com.edti.exceltoxml.Exceptions.NullAnswerException;
import com.edti.exceltoxml.Models.Category.Category;
import com.edti.exceltoxml.Models.Category.Info;
import com.edti.exceltoxml.Models.Question.*;
import com.edti.exceltoxml.Models.Question.File;
import com.edti.exceltoxml.Models.Question.Name;
import com.edti.exceltoxml.Models.Quiz;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IQuestionService;
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
import java.util.*;

@Service
public class QuestionService implements IQuestionService {

    private List<SubQuestion> subQuestionList;
    private List<Dragbox> dragboxList;
    private List<Answer> answerList;

    private String questionType;

    private IImageService imageService;

    @Autowired
    public QuestionService(IImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public Quiz createObjectFromExcel(Workbook workbook) throws IOException {
        Quiz currentQuiz = new Quiz();
        Question category = new Question("category", new Category("$course$/top/Databases alapbeállítása/Bigtest1"), new Info(""), "");
        List<Question> questions = new ArrayList<>();
        questions.add(category);

        workbook.setActiveSheet(0);


        for (Sheet sheet : workbook) {
            switch (sheet.getSheetName()) {
                case "igaz-hamis" -> {
                    System.out.println("Igaz hamis started");
                    removeFirstRow(sheet);
                    this.questionType = "igaz-hamis";
                    for (Row row : sheet) {
                        questions.add(createTrueFalse(row));
                    }
                    System.out.println("Igaz hamis done");
                }

                case "párosító" -> {
                    System.out.println("Párosító started");
                    removeFirstRow(sheet);
                    this.questionType = "párosító";

                    for (Row row : sheet) {
                        questions.add(createMatching(row));
                    }
                    System.out.println("Párosító done!");
                }

                case "feleletválasztó" -> {
                    System.out.println("Feleletválasztó started!");
                    removeFirstRow(sheet);
                    this.questionType = "feleletválasztó";

                    for (Row row : sheet) {
                        questions.add(createMultichoice(row));
                    }
                    System.out.println("Feleletválasztó done!");
                }

                case "szövegbehúzás" -> {
                    System.out.println("Szövegbehúzás started");
                    removeFirstRow(sheet);
                    this.questionType = "szövegbehúzás";

                    for (Row row : sheet) {
                        questions.add(createDdwtos(row));
                    }
                }

                default -> {
                    System.out.println("It's over Anakin!");
                }
            }

            currentQuiz.setQuestion(questions);
        }
        return currentQuiz;
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

    @Override
    public String createImageXmlFromStringXml(java.io.File inputXml) throws IOException, JAXBException {

        Quiz quiz = createQuizFromXml(inputXml);


        for (Question question : quiz.getQuestion()) {
            switch (question.getType()) {
                case "multichoice" -> replaceQuestionAndAnswer(question);
                case "truefalse" -> {
                    String questionText = getStringFromHTML(question.getQuestiontext().getText());

                    question.setQuestiontext(new QuestionText("html", new File("base64",
                            imageService.transformStringToBase64(questionText))));
                }
                case "matching" -> {
                    String questionText = getStringFromHTML(question.getQuestiontext().getText());

                    question.setQuestiontext(new QuestionText("html", new File("base64",
                            imageService.transformStringToBase64(questionText))));

                    for (SubQuestion subQuestion : question.getSubquestion()) {
                        subQuestion.setText(imageService.transformStringToBase64(subQuestion.getText()));
                    }
                }
                default -> System.out.println("Unhandled question type");
            }
        }

        return createXmlFromQuiz(quiz);
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

        question.setQuestiontext(new QuestionText("html", new File("base64",
                imageService.transformStringToBase64(questionText))));

        for (Answer answer : question.getAnswer()) {
            String answerText = getStringFromHTML(answer.getText());
            answer.setText("<p dir=\"ltr\" style=\"text-align: left;\"><img src=\"@@PLUGINFILE@@/imageName\" alt=\"\" role=\"presentation\" class=\"img-fluid\"><br></p>");
            answer.setFile(new File("base64", imageService.transformStringToBase64(answerText)));
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
                        new File("base64", imageService.transformStringToBase64(cell.getStringCellValue()))));
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
                case 2 -> question.setQuestiontext(new QuestionText("html", new File("base64", imageService.transformStringToBase64(row.getCell(i).getStringCellValue()))));
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
                case 2 -> question.setQuestiontext(new QuestionText("html", new File("base64", imageService.transformStringToBase64(row.getCell(i).getStringCellValue()))));
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
