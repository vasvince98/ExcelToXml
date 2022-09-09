package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Exceptions.MissingDataException;
import com.edti.exceltoxml.Exceptions.MissingHeaderException;
import com.edti.exceltoxml.Exceptions.NullAnswerException;
import com.edti.exceltoxml.Models.Category.Category;
import com.edti.exceltoxml.Models.Category.Info;
import com.edti.exceltoxml.Models.Question.*;
import com.edti.exceltoxml.Models.Question.Name;
import com.edti.exceltoxml.Models.Quiz;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.marshaller.NoEscapeHandler;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
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
                                        new File("base64", imageService.imageToBase64(imageService.renderStringToImage(cell.getStringCellValue())))));
                                case 3 -> question.setDefaultgrade(cell.getNumericCellValue());
                                case 4 -> question.setAnswer(createAnswers(this.questionType, cell.toString().toLowerCase()));
                                default -> System.out.println("It's over Anakin!");
                            }
                            i++;
                        }
                        questions.add(question);
                    }
                    System.out.println("Igaz hamis done");
                }

                case "párosító" -> {
                    System.out.println("Párosító started");
                    this.questionType = "párosító";
                    removeFirstRow(sheet);

                    for (Row row : sheet) {

                        Question question = new Question();
                        subQuestionList = new ArrayList<>();
                        for (int i = 0; i < 13; i++) {
                            switch (i) {
                                case 0 -> question.setType("matching");
                                case 1 -> question.setName(new Name(row.getCell(i).getStringCellValue()));
                                case 2 -> question.setQuestiontext(new QuestionText("html", new File("base64", imageService.imageToBase64(imageService.renderStringToImage(row.getCell(i).getStringCellValue())))));
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
                        questions.add(question);
                        System.out.println(questions.get(1).getDefaultgrade());
                    }
                }

                case "feleletválasztó" -> {
                    this.questionType = "feleletválasztó";
                    removeFirstRow(sheet);

                    for (Row row : sheet) {

                        Question question = new Question();
                        this.answerList = new ArrayList<>();
                        for (int i = 0; i < 12; i++) {

                            switch (i) {
                                case 0 -> question.setType("multichoice");
                                case 1 -> question.setName(new Name(row.getCell(i).getStringCellValue()));
                                case 2 -> question.setQuestiontext(new QuestionText("html", new File("base64", imageService.imageToBase64(imageService.renderStringToImage(row.getCell(i).getStringCellValue())))));
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
                        questions.add(question);
                    }
                }

                case "szövegbehúzás" -> {
                    this.questionType = "szövegbehúzás";
                    removeFirstRow(sheet);

                    for (Row row : sheet) {

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
                        questions.add(question);
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
    public String createXmlFromObject(Quiz quiz) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Quiz.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        CharacterEscapeHandler escapeHandler = NoEscapeHandler.theInstance;
        jaxbMarshaller.setProperty("com.sun.xml.bind.characterEscapeHandler", escapeHandler);

        StringWriter sw = new StringWriter();

        jaxbMarshaller.marshal(quiz, sw);

        String xmlContent = sw.toString();
        System.out.println(xmlContent);
        return xmlContent;
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
}
