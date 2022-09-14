package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Exceptions.MissingFileException;
import com.edti.exceltoxml.Models.Quiz;
import com.edti.exceltoxml.Models.RenderAPI;
import com.edti.exceltoxml.Services.IImageService;
import com.edti.exceltoxml.Services.IQuestionService;
import com.edti.exceltoxml.Services.IUploadService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.rmi.server.RemoteRef;
import java.util.Arrays;
import java.util.Base64;

@RestController
@PropertySource(value = "/global.properties")
public class MainRestController {

    @Value("${serverStorePath}")
    private String serverStoreFolder;

    String filePath;

    private final IQuestionService questionService;
    private final IImageService imageService;

    @Autowired
    public MainRestController(IQuestionService questionService, IImageService imageService) {
        this.questionService = questionService;
        this.imageService = imageService;
    }

    @RequestMapping("/excel")
    public String generateXmlFromExcel() {
        try {
            File folder = new File(serverStoreFolder);
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.getName().equals("datumtesztk.xlsx")) {              //todo: több felhasználónál felkészülni tövv fájlra
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
    public String generateXmlFromXml() {
        try {
            File folder = new File(serverStoreFolder);
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.getName().toLowerCase().endsWith(".xml")) {             //todo: több felhasználónál felkészülni tövv fájlra (prototype)
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
    public ResponseEntity<InputStreamResource> getFile(HttpServletResponse response) {
        try {
            File folder = new File(serverStoreFolder);
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            String fileName = null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.getName().toLowerCase().endsWith(".xml")) {             //todo: több felhasználónál felkészülni tövv fájlra (prototype)
                    filePath = listOfFile.getPath();
                    fileName = listOfFile.getName();
                }
            }

            File inputXml = new File(filePath);

            File localSaveFile = new File(serverStoreFolder + "/" + fileName);

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
