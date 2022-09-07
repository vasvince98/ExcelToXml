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
            String serverPath = currDir.getAbsolutePath() + file.getOriginalFilename();
            FileOutputStream server = new FileOutputStream(serverPath);

            String localPath = System.getProperty("os.name").equals("Windows 10") ? (System.getProperty("user.home") + "/Desktop") : ("/Users/vasvince/Desktop");
            FileOutputStream local = new FileOutputStream(localPath + "/heloooo" + file.getOriginalFilename());

            int ch = 0;
            while ((ch = in.read()) != -1) {
                //Store the file in server
                server.write(ch);
                //Put the file to desktop
//                local.write(ch);
            }
            server.flush();
            local.flush();
            server.close();
            local.close();
        } catch (IOException e) {
            //fixme: exceptions
            System.out.println(e);
        }


    }
}
