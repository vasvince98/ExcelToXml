package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Models.Category.Category;
import com.edti.exceltoxml.Models.Category.Info;
import com.edti.exceltoxml.Models.Question.*;
import com.edti.exceltoxml.Models.Quiz;
import com.edti.exceltoxml.Services.IQuestionService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@PropertySource(value = "/global.properties")
public class TestController {
    @Value("${fileLocation}")
    private String fileLocation;

    private IQuestionService questionService;

    @Autowired
    public TestController(IQuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping("/")
    public String test() throws JAXBException, IOException {

        FileInputStream file = new FileInputStream(fileLocation);
        Workbook workbook = new XSSFWorkbook(file);

        Quiz currentQuiz = questionService.createObjectFromExcel(workbook);
        System.out.println(currentQuiz);
        return questionService.createXmlFromObject(currentQuiz);
    }
}
