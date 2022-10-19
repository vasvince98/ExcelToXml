package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Exceptions.MissingFileException;
import com.edti.exceltoxml.Services.Interfaces.IQuestionService;
import com.edti.exceltoxml.Services.Interfaces.IUploadAndDownloadService;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;

@Service
public class UploadAndDownloadService implements IUploadAndDownloadService {

    private final PathLocatorService pathLocatorService;
    private final IQuestionService questionService;
    private String filePath;
    private String fileName;

    private ResponseEntity<ByteArrayResource> responseEntity;

    @Autowired
    public UploadAndDownloadService(PathLocatorService pathLocatorService, IQuestionService questionService) {
        this.pathLocatorService = pathLocatorService;
        this.questionService = questionService;
    }

    @Override
    public void handleExcelFile(MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            String serverPath = pathLocatorService.getPath();
            File currDir = new File(serverPath + "/" + LocalDateTime.now() + file.getOriginalFilename());
            FileOutputStream server = new FileOutputStream(currDir);

            int ch = 0;
            while ((ch = in.read()) != -1) {
                server.write(ch);
            }
            server.flush();
            server.close();
        } catch (IOException e) {
            //fixme: exceptions
            System.out.println(e);
        }


    }

    @Override
    public void convertFileToSimpleXml() {
        try {
            File localSaveFile = getFileFromServer();
            setResponseEntity(new ByteArrayResource(questionService.createXmlFromExcel(WorkbookFactory.create(localSaveFile)).getBytes()));

        } catch (Exception e) {
            e.printStackTrace();
            throw new MissingFileException("Nem töltött fel fájlt!");
        }
    }

    @Override
    public void convertFileToImageXml() {
        try {
            File localSaveFile = getFileFromServer();
            setResponseEntity(new ByteArrayResource(questionService.createXmlFromExcel(WorkbookFactory.create(localSaveFile)).getBytes()));

        } catch (Exception e) {
            e.printStackTrace();
            throw new MissingFileException("Nem töltött fel fájlt!");
        }
    }

    @Override
    public ResponseEntity<ByteArrayResource> downloadFile() {
        return this.responseEntity;
    }

    @Override
    public void deleteFiles() {
        File outputFolder = new File(pathLocatorService.getPath());
        File[] files = outputFolder.listFiles();


        if (files != null) {
            for (File f : files) {
                if(f.getName().toLowerCase().endsWith(".xml") && !f.getName().endsWith("pom.xml") ||
                        f.getName().toLowerCase().endsWith(".xlsx")) {
                    f.delete();
                }
            }
        }
    }


    private File getFileFromServer() {

        File folder = new File(pathLocatorService.getPath());
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.getName().toLowerCase().endsWith(".xml") && !listOfFile.getName().toLowerCase().endsWith("om.xml") ||
                    listOfFile.getName().toLowerCase().endsWith(".xlsx")) {
                this.filePath = listOfFile.getPath();
                this.fileName = listOfFile.getName();
            }
        }
        return new File(filePath);
    }


    private void setResponseEntity(ByteArrayResource resource) {
        if (fileName.toLowerCase().endsWith(".xml")) {
            this.responseEntity = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + this.fileName)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            this.responseEntity = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "CONVERTED.xml")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
    }
}
