package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Services.Interfaces.IUploadService;
import com.edti.exceltoxml.Services.PathLocatorService;
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
import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@PropertySource(value = {"/application.properties", "/global.properties"})
public class MainController {

    @Value("${server.port}")
    private int port;

    @Value("${serverStorePath}")
    private String serverStoreFolder;

    private IUploadService uploadService;
    private PathLocatorService pathLocatorService;

    @Autowired
    public MainController(IUploadService uploadService, PathLocatorService pathLocatorService) {
        this.uploadService = uploadService;
        this.pathLocatorService = pathLocatorService;
    }

    @RequestMapping("/upload")
    public String uploadFile() throws URISyntaxException {
        File outputFolder = new File(pathLocatorService.getPath());
        File[] files = outputFolder.listFiles();

        if (files != null) {
            for (File f : files) {
                if(f.getName().toLowerCase().endsWith(".xml") && !f.getName().endsWith("pom.xml")) {
                    f.delete();
                }
            }
        }

        return "index";
    }

    @RequestMapping(path = "/postupload", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public RedirectView uploadResponse(Model m, @RequestParam("file") MultipartFile file) throws IOException, URISyntaxException {
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
