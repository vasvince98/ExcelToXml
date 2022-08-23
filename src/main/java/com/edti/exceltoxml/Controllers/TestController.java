package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Models.Question.*;
import com.edti.exceltoxml.Models.Quiz;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @RequestMapping("/")
    public String test() throws JAXBException {
        Quiz quiz = new Quiz();

        Question question1 = new Question("multichoice", new Name("What are the constraints?"),
                new QuestionText("What are the constraints?"),
                new GeneralFeedback(""),
                1.0,
                0.33,
                0,
                "",
                true,
                true,
                "abc",
                0,
                new CorrectFeedback(""),
                new PartiallyCorrectFeedback(""),
                new IncorrectFeedback(""));

        Answer answer1 = new Answer("Constraints are keys specifying an attribute or attributes", new Feedback(""));
        Answer answer2 = new Answer("Másik válasz", new Feedback(""));
        Answer answer3 = new Answer("Másik asjnass", new Feedback(""));
        List<Answer> answers = new ArrayList<>(List.of(answer1, answer2, answer3));
        question1.setAnswers(answers);

        Question question2 = new Question("multichoice", new Name("Második kérdés"),
                new QuestionText("Második kérdés"),
                new GeneralFeedback(""),
                1.0,
                0.33,
                0,
                "",
                true,
                true,
                "abc",
                0,
                new CorrectFeedback(""),
                new PartiallyCorrectFeedback(""),
                new IncorrectFeedback(""));

        question2.setAnswers(answers);

        List<Question> questions = new ArrayList<>(List.of(question1, question2));

        quiz.setQuestion(questions);

        JAXBContext jaxbContext = JAXBContext.newInstance(Quiz.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();

        jaxbMarshaller.marshal(quiz, sw);

        String xmlContent = sw.toString();
        System.out.println(xmlContent);
        return xmlContent;
    }
}
