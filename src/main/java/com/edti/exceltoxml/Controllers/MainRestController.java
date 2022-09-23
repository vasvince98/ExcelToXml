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

    private MainController state;

    String filePath;

    private final IQuestionService questionService;
    private final IImageService imageService;
    private PathLocatorService pathLocatorService;

    @Autowired
    public MainRestController(IQuestionService questionService,
                              IImageService imageService,
                              PathLocatorService pathLocatorService,
                              MainController state) throws URISyntaxException {
        this.questionService = questionService;
        this.imageService = imageService;
        this.pathLocatorService = pathLocatorService;
        this.state = state;
    }

    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> getFile() throws URISyntaxException {
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

            File localSaveFile = new File(filePath);

            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(questionService.createImageXmlFromStringXml(localSaveFile).getBytes()));

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


}
