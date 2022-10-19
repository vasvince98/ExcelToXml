package com.edti.exceltoxml.Services.Interfaces;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public interface IUploadAndDownloadService {
    void handleExcelFile(MultipartFile file) throws IOException, URISyntaxException;
    void convertFileToSimpleXml();
    void convertFileToImageXml();
    ResponseEntity<ByteArrayResource> downloadFile();
    void deleteFiles();
}
