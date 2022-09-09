package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Exceptions.MissingFileException;
import com.edti.exceltoxml.Models.Quiz;
import com.edti.exceltoxml.Models.RenderAPI;
import com.edti.exceltoxml.Services.IImageService;
import com.edti.exceltoxml.Services.IQuestionService;
import com.edti.exceltoxml.Services.IUploadService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;
import java.awt.image.BufferedImage;
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
    private final IImageService imageService;

    @Autowired
    public MainRestController(IQuestionService questionService, IImageService imageService) {
        this.questionService = questionService;
        this.imageService = imageService;
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
            System.out.println(filePath);
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);

            Quiz currentQuiz = questionService.createObjectFromExcel(workbook);
            System.out.println(currentQuiz);
            return questionService.createXmlFromObject(currentQuiz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MissingFileException("Nem töltött fel fájlt!");
        }

    }

    @RequestMapping("/getos")
    public String getOS() {
        String os = System.getProperty("os.name");
         System.out.println("Using System Property: " + os);
        return os;
    }

    @RequestMapping("/base64")
    public String base64() throws IOException {


        BufferedImage image = imageService.renderStringToImage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");

        ImageIO.write(image, "png", new File("/Users/vasvince/Desktop/image.png"));

        return imageService.imageToBase64(image);

    }



}
