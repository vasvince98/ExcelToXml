package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Exceptions.MissingHeaderException;
import com.edti.exceltoxml.Models.AuxClasses.AbstractAnswer;
import com.edti.exceltoxml.Models.AuxClasses.Answer;
import com.edti.exceltoxml.Models.AuxClasses.Questiontext;
import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.PropertyClasses.FieldProperties;
import com.edti.exceltoxml.Models.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.QuestionTypes.RealQuestion;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IQuestionService;
import com.edti.exceltoxml.Services.QuestionObjectProviders.DdwtosQuestionProvider;
import com.edti.exceltoxml.Services.QuestionObjectProviders.MatchingQuestionProvider;
import com.edti.exceltoxml.Services.QuestionObjectProviders.MultichoiceQuestionProvider;
import com.edti.exceltoxml.Services.QuestionObjectProviders.TrueFalseQuestionProvider;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;

@Service
public class QuestionService implements IQuestionService {

    private List<Answer> answerList;

    private String questionType;

    private String finalXml;


    private final IImageService imageService;
    private final MultichoiceQuestionProvider multichoiceQuestionProvider;
    private final TrueFalseQuestionProvider trueFalseQuestionProvider;
    private final MatchingQuestionProvider matchingQuestionProvider;
    private final DdwtosQuestionProvider ddwtosQuestionProvider;

    @Autowired
    public QuestionService(IImageService imageService,
                           MultichoiceQuestionProvider multichoiceQuestionProvider,
                           TrueFalseQuestionProvider trueFalseQuestionProvider,
                           MatchingQuestionProvider matchingQuestionProvider,
                           DdwtosQuestionProvider ddwtosQuestionProvider) {
        this.imageService = imageService;
        this.multichoiceQuestionProvider = multichoiceQuestionProvider;
        this.trueFalseQuestionProvider = trueFalseQuestionProvider;
        this.matchingQuestionProvider = matchingQuestionProvider;
        this.ddwtosQuestionProvider = ddwtosQuestionProvider;
    }



    @Override
    public String createXmlFromExcel(Workbook workbook) {
        List<Map<Cat, List<RealQuestion>>> questionList = new ArrayList<>();
        finalXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<quiz>\n";
        workbook.setActiveSheet(0);




        for (Sheet sheet : workbook) {
            switch (sheet.getSheetName()) {
                case "feleletválasztó" -> questionList.add(multichoiceQuestionProvider.objectListFromSheet(sheet, QType.multichoice));
                case "igazhamis" -> questionList.add(trueFalseQuestionProvider.objectListFromSheet(sheet, QType.truefalse));
                case "párosító" -> questionList.add(matchingQuestionProvider.objectListFromSheet(sheet, QType.matching));
                case "szövegbehúzás" -> questionList.add(ddwtosQuestionProvider.objectListFromSheet(sheet, QType.ddwtos));
                default -> System.out.println("Nincs még lekezelve");
            }

        }


        return createFinalXml(questionList);
    }


    private String createFinalXml(List<Map<Cat, List<RealQuestion>>> questionList) {

        questionList.forEach((map) -> {
            map.forEach(((cat, realQuestions) -> {
                try {
                    finalXml = finalXml.concat(cat.getXmlForm());
                    realQuestions.forEach((realQuestion -> {
                        try {
                            finalXml = finalXml.concat(realQuestion.getXmlForm());
                        } catch (JAXBException e) {
                            throw new RuntimeException(e);
                        }
                    }));
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
            }));
        });
        finalXml = finalXml.concat("\n</quiz>");
        return finalXml;
    }


    private String getStringFromHTML(String html) {
        return Jsoup.parse(html).text();
    }

    private void replaceQuestionAndAnswer(RealQuestion question) throws IOException {
        String questionText = getStringFromHTML(question.getQuestiontext().getText());

        question.setQuestiontext(new Questiontext(questionText));

        for (AbstractAnswer answer : question.getAnswer()) {
            String answerText = getStringFromHTML(answer.getText());
            answer.setText(String.format("<img src=\"data:image/png;base64,%s\"/>",imageService.transformStringToBase64(answerText)));
        }
    }
}
