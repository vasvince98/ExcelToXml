package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Models.Quiz;
import com.edti.exceltoxml.Services.IQuestionService;
import com.edti.exceltoxml.Services.IUploadService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.xml.bind.JAXBException;
import java.io.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@PropertySource(value = "/global.properties")
public class TestRestController {
    @Value("${fileLocation}")
    private String fileLocation;

    String filePath;

    private IQuestionService questionService;
    private IUploadService uploadService;

    @Autowired
    public TestRestController(IQuestionService questionService, IUploadService uploadService) {
        this.questionService = questionService;
        this.uploadService = uploadService;
    }

    @RequestMapping("/")
    public String test() throws JAXBException, IOException {
        File folder = new File(fileLocation);
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.getName().equals("datufile.xlsx")) {
                filePath = listOfFile.getPath();
            }
        }

        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);

        Quiz currentQuiz = questionService.createObjectFromExcel(workbook);
        System.out.println(currentQuiz);
        return questionService.createXmlFromObject(currentQuiz);
    }



}
