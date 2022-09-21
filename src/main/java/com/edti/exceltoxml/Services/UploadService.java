package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Services.Interfaces.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
@PropertySource(value = "/global.properties")
public class UploadService implements IUploadService {

    @Value("${serverStorePath}")
    private String serverStoreFolder;

    private final PathLocatorService pathLocatorService;

    @Autowired
    public UploadService(PathLocatorService pathLocatorService) {
        this.pathLocatorService = pathLocatorService;
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
}
