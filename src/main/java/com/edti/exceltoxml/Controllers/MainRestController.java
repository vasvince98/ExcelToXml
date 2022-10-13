package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Exceptions.MissingFileException;
import com.edti.exceltoxml.Models.PropertyClasses.FieldProperties;
import com.edti.exceltoxml.Models.PropertyClasses.GlobalProperties;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IQuestionService;
import com.edti.exceltoxml.Services.Interfaces.IUploadAndDownloadService;
import com.edti.exceltoxml.Services.PathLocatorService;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.*;
import java.net.URISyntaxException;

@RestController
@PropertySource(value = "/global.properties")
public class MainRestController {


    private final IUploadAndDownloadService uploadAndDownloadService;

    @Autowired
    public MainRestController(IUploadAndDownloadService uploadAndDownloadService) {
        this.uploadAndDownloadService = uploadAndDownloadService;
    }

    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() {
        return uploadAndDownloadService.convertAndDownloadFile();
    }

    @RequestMapping("/getos")
    public String getOS() {
        String os = System.getProperty("os.name");
         System.out.println("Using System Property: " + os);
        return os;
    }


}
