package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Exceptions.MissingHeaderException;
import com.edti.exceltoxml.Models.AuxClasses.Answer;
import com.edti.exceltoxml.Models.AuxClasses.Questiontext;
import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.QuestionTypes.RealQuestion;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IQuestionService;
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

    @Autowired
    public QuestionService(IImageService imageService,
                           MultichoiceQuestionProvider multichoiceQuestionProvider,
                           TrueFalseQuestionProvider trueFalseQuestionProvider) {
        this.imageService = imageService;
        this.multichoiceQuestionProvider = multichoiceQuestionProvider;
        this.trueFalseQuestionProvider = trueFalseQuestionProvider;
    }



    @Override
    public String createXmlFromExcel(Workbook workbook) {
        List<Map<Cat, List<RealQuestion>>> questionList = new ArrayList<>();
        finalXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<quiz>\n";
        workbook.setActiveSheet(0);
        Map<Cat, List<RealQuestion>> multichoiceMap;
        Map<Cat, List<RealQuestion>> truefalseMap;


        for (Sheet sheet : workbook) {
            switch (sheet.getSheetName()) {
                case "feleletválasztó" ->  {
                    multichoiceMap = multichoiceQuestionProvider.objectListFromSheet(sheet, QType.multichoice);
                    questionList.add(multichoiceMap);
                }
                case "igazhamis" -> {
                    truefalseMap = trueFalseQuestionProvider.objectListFromSheet(sheet, QType.truefalse);
                    questionList.add(truefalseMap);
                }
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
                        } catch (JAXBException | FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }));
                } catch (JAXBException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }));
        });
        finalXml = finalXml.concat("\n</quiz>");
        return finalXml;
    }

    private void removeFirstRow(Sheet sheet) {
        try {
            sheet.removeRow(sheet.getRow(0));
            System.out.println("First row deleted!");
        } catch (Exception e) {
            throw new MissingHeaderException("Az első sorban szerepelnie kell a mező típusoknak!");
        }

    }

    private String getStringFromHTML(String html) {
        return Jsoup.parse(html).text();
    }

    private void replaceQuestionAndAnswer(RealQuestion question) throws IOException {
        String questionText = getStringFromHTML(question.getQuestiontext().getText());

        question.setQuestiontext(new Questiontext(questionText));

        for (Answer answer : question.getAnswer()) {
            String answerText = getStringFromHTML(answer.getText());
            answer.setText(String.format("<img src=\"data:image/png;base64,%s\"/>",imageService.transformStringToBase64(answerText)));
        }
    }
}
