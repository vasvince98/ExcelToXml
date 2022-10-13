package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Exceptions.MissingFileException;
import com.edti.exceltoxml.Services.Interfaces.IQuestionService;
import com.edti.exceltoxml.Services.Interfaces.IStateService;
import com.edti.exceltoxml.Services.Interfaces.IUploadAndDownloadService;
import com.edti.exceltoxml.Services.PathLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@PropertySource(value = {"/application.properties", "/global.properties"})
public class MainController {

    String filePath;

    @Value("${server.port}")
    private int port;

    private final IUploadAndDownloadService uploadAndDownloadService;
    private final IStateService stateService;
    private final IQuestionService questionService;

    @Autowired
    public MainController(IUploadAndDownloadService uploadAndDownloadService,
                          IStateService stateService,
                          IQuestionService questionService) {
        this.uploadAndDownloadService = uploadAndDownloadService;
        this.stateService = stateService;
        this.questionService = questionService;
    }

    @RequestMapping("/")
    public String uploadFile() {
        uploadAndDownloadService.deleteFiles();
        return "index";
    }

    @RequestMapping(path = "/postupload", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public ModelAndView uploadResponse(@RequestParam("file") MultipartFile file,
                                       @Nullable @RequestParam("checkBox") String isPicture) throws IOException, URISyntaxException {

        stateService.setState(isPicture);

        uploadAndDownloadService.handleExcelFile(file);
        uploadAndDownloadService.convertFile();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect");
        return mav;
    }



    @RequestMapping("/EDTI")
    public String EDTI() {
        return "EDTI";
    }

}
