package com.edti.exceltoxml.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@PropertySource(value = "/global.properties")
public class UploadService implements IUploadService {

    @Value("${fileLocation}")
    private String fileLocation;


    @Override
    public void handleExcelFile(MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            File currDir = new File(fileLocation + "/datum");           //todo: filename
            String path = currDir.getAbsolutePath() + file.getOriginalFilename();
            FileOutputStream f = new FileOutputStream(path);
            int ch = 0;
            while ((ch = in.read()) != -1) {
                f.write(ch);
            }
            f.flush();
            f.close();
        } catch (IOException e) {
            //fixme: exceptions
            System.out.println(e);
        }

    }
}
