package com.edti.exceltoxml.Services.Interfaces;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IUploadAndDownloadService {
    void handleExcelFile(MultipartFile file) throws IOException, URISyntaxException;
    void convertFile();
    ResponseEntity<ByteArrayResource> downloadFile();
    void deleteFiles();
}
