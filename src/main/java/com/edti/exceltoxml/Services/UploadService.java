package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Controllers.MainController;
import com.edti.exceltoxml.Main;
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

    private PathLocatorService pathLocatorService;

    @Autowired
    public UploadService(PathLocatorService pathLocatorService) {
        this.pathLocatorService = pathLocatorService;
    }

    @Override
    public void handleExcelFile(MultipartFile file) throws URISyntaxException {
        try {
            InputStream in = file.getInputStream();
            String serverPath = pathLocatorService.getPath();
            File currDir = new File(serverPath + "/" + LocalDateTime.now() + file.getOriginalFilename());           //todo: filename
            FileOutputStream server = new FileOutputStream(currDir);

//            String localPath = System.getProperty("os.name").contains("windows") ? (System.getProperty("user.home") + "/Desktop") : ("/Users/vasvince/Desktop");
//            FileOutputStream local = new FileOutputStream(localPath + "/heloooo" + file.getOriginalFilename());

            int ch = 0;
            while ((ch = in.read()) != -1) {
                //Store the file in server
                server.write(ch);
                //Put the file to desktop
//                local.write(ch);
            }
            server.flush();
//            local.flush();
            server.close();
//            local.close();
        } catch (IOException e) {
            //fixme: exceptions
            System.out.println(e);
        }


    }
}
