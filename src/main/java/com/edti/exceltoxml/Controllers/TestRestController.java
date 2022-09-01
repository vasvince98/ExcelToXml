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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.xml.bind.JAXBException;
import java.io.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@PropertySource(value = "/global.properties")
public class TestRestController {
    @Value("${fileLocation}")
    private String fileLocation;

    private IQuestionService questionService;
    private IUploadService uploadService;

    @Autowired
    public TestRestController(IQuestionService questionService, IUploadService uploadService) {
        this.questionService = questionService;
        this.uploadService = uploadService;
    }

    @RequestMapping("/")
    public String test() throws JAXBException, IOException {

        FileInputStream file = new FileInputStream(fileLocation);
        Workbook workbook = new XSSFWorkbook(file);

        Quiz currentQuiz = questionService.createObjectFromExcel(workbook);
        System.out.println(currentQuiz);
        return questionService.createXmlFromObject(currentQuiz);
    }



    @RequestMapping(path = "/postupload", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String uploadResponse(Model m, @RequestPart MultipartFile file) throws IOException {
        uploadService.handleExcelFile(file);
        return "Szia";
    }


}
