package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Models.Category.Category;
import com.edti.exceltoxml.Models.Category.Info;
import com.edti.exceltoxml.Models.Dummy;
import com.edti.exceltoxml.Models.Question.*;
import com.edti.exceltoxml.Models.Quiz;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService implements IQuestionService {

    private String questionType;

    @Override
    public Quiz createObjectFromExcel(Workbook workbook) {
        Quiz currentQuiz = new Quiz();
        Question category = new Question("category", new Category("$course$/top/Databases alapbeállítása/Bigtest1"), new Info(""), "");
        List<Question> questions = new ArrayList<>();
        questions.add(category);

        workbook.setActiveSheet(0);

        if (workbook.getSheetName(0).equals("igaz-hamis")) {
            System.out.println("Jó az oldal");
            this.questionType = "igaz-hamis";
        }


        for (Sheet sheet : workbook) {

            for (Row row : sheet) {
                int i = 0;
                Question question = new Question();
                for (Cell cell : row) {
                    switch (i) {
                        case 0 -> question.setType("truefalse");
                        case 1 -> question.setName(new Name(cell.getStringCellValue()));
                        case 2 -> question.setQuestionText(new QuestionText(cell.getStringCellValue(), "html"));
                        case 3 -> question.setDefaultGrade(cell.getNumericCellValue());
                        case 4 -> question.setAnswer(createAnswers(this.questionType, cell.toString().toLowerCase()));
                        default -> System.out.println("It's over Anakin!");
                    }
                    i++;
                }
                questions.add(question);
            }
        }
        currentQuiz.setQuestion(questions);
        return currentQuiz;
    }

    @Override
    public String createXmlFromObject(Quiz quiz) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Quiz.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

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

        System.out.println(solution);

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
}
