package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Exceptions.MissingFileException;
import com.edti.exceltoxml.Models.Quiz;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IQuestionService;
import com.edti.exceltoxml.Services.PathLocatorService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;

@RestController
@PropertySource(value = "/global.properties")
public class MainRestController {

    @Value("${serverStorePath}")
    private String serverStoreFolder;


    String filePath;

    private final IQuestionService questionService;
    private final IImageService imageService;
    private PathLocatorService pathLocatorService;

    @Autowired
    public MainRestController(IQuestionService questionService, IImageService imageService, PathLocatorService pathLocatorService) throws URISyntaxException {
        this.questionService = questionService;
        this.imageService = imageService;
        this.pathLocatorService = pathLocatorService;
    }

    @RequestMapping("/excel")
    public String generateXmlFromExcel() {
        try {
            File folder = new File("");
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.getName().toLowerCase().endsWith(".xlsx")) {              //todo: több felhasználónál felkészülni tövv fájlra
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

    @RequestMapping("/xml")
    public String generateXmlFromXml() throws URISyntaxException {
        try {
            File folder = new File(pathLocatorService.getPath());
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.getName().toLowerCase().endsWith(".xml")) {
                    filePath = listOfFile.getPath();
                }
            }

            File inputXml = new File(filePath);

            return questionService.createImageXmlFromStringXml(inputXml);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MissingFileException("Nem töltött fel fájlt!");
        }
    }

    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> getFile(HttpServletResponse response) throws URISyntaxException {
        try {
            File folder = new File(pathLocatorService.getPath());
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            String fileName = null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.getName().toLowerCase().endsWith(".xml") && !listOfFile.getName().toLowerCase().endsWith("om.xml")) {             //todo: több felhasználónál felkészülni tövv fájlra (prototype)
                    filePath = listOfFile.getPath();
                    fileName = listOfFile.getName();
                }
            }

            File inputXml = new File(filePath);

            File localSaveFile = new File(filePath);

            String finalXML = questionService.createImageXmlFromStringXml(inputXml);

            InputStreamResource resource = new InputStreamResource(new FileInputStream(localSaveFile));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

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
