package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Services.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@PropertySource(value = {"/application.properties", "/global.properties"})
public class MainController {

    @Value("${server.port}")
    private int port;

    @Value("${serverStorePath}")
    private String serverStoreFolder;

    private IUploadService uploadService;

    @Autowired
    public MainController(IUploadService uploadService) {
        this.uploadService = uploadService;
    }

    @RequestMapping("/upload")
    public String uploadFile() {
        File outputFolder = new File(serverStoreFolder);
        File[] files = outputFolder.listFiles();

        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }

        return "index";
    }

    @RequestMapping(path = "/postupload", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public RedirectView uploadResponse(Model m, @RequestParam("file") MultipartFile file) throws IOException {
        uploadService.handleExcelFile(file);
        return new RedirectView(String.format("http://localhost:%s/redirect", port));
    }

    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect";
    }

    @RequestMapping("/EDTI")
    public String EDTI() {
        return "EDTI";
    }
}
