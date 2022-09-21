package com.edti.exceltoxml.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface IUploadService {
    void handleExcelFile(MultipartFile file) throws IOException, URISyntaxException;
}
