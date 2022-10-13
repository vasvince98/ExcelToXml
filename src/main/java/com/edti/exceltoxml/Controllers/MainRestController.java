package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Services.Interfaces.IUploadAndDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@PropertySource(value = "/global.properties")
public class MainRestController {


    private final IUploadAndDownloadService uploadAndDownloadService;

    @Autowired
    public MainRestController(IUploadAndDownloadService uploadAndDownloadService) {
        this.uploadAndDownloadService = uploadAndDownloadService;
    }

    @RequestMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile() {
        return uploadAndDownloadService.downloadFile();
    }

    @RequestMapping("/getos")
    public String getOS() {
        String os = System.getProperty("os.name");
         System.out.println("Using System Property: " + os);
        return os;
    }


}
