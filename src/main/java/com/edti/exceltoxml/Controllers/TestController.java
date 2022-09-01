package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Services.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class TestController {

    private IUploadService uploadService;

    @Autowired
    public TestController(IUploadService uploadService) {
        this.uploadService = uploadService;
    }

    @RequestMapping("/upload")
    public String uploadFile() {
        return "index";
    }

    @RequestMapping(path = "/postupload", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public RedirectView uploadResponse(Model m, @RequestParam("file") MultipartFile file) throws IOException {
        uploadService.handleExcelFile(file);
        return new RedirectView("http://localhost:8080/redirect");
    }

    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect";
    }
}
