package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Exceptions.MissingFileException;
import com.edti.exceltoxml.Models.Quiz;
import com.edti.exceltoxml.Services.IQuestionService;
import com.edti.exceltoxml.Services.IUploadService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;


import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.Arrays;
import java.util.Base64;

@RestController
@PropertySource(value = "/global.properties")
public class MainRestController {
    @Value("${fileLocation}")
    private String fileLocation;

    String filePath;

    private final IQuestionService questionService;

    @Autowired
    public MainRestController(IQuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping("/")
    public String test() throws JAXBException, IOException {
        try {
            File folder = new File(fileLocation);
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.getName().equals("datumtesztk.xlsx")) {
                    filePath = listOfFile.getPath();
                }
            }
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);

            Quiz currentQuiz = questionService.createObjectFromExcel(workbook);
            System.out.println(currentQuiz);
            return questionService.createXmlFromObject(currentQuiz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MissingFileException("Nem töltött fel fájlt! Stack trace: ");
        }

    }

    @RequestMapping("/getos")
    public String getOS() {
        String os = System.getProperty("os.name");
         System.out.println("Using System Property: " + os);
        return os;
    }

    @RequestMapping("/base64")
    public String base64() {
        String originalInput = "test input";
        return Base64.getEncoder().encodeToString(originalInput.getBytes());
    }



}
