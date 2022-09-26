package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Exceptions.MissingFileException;
import com.edti.exceltoxml.Services.ImageService;
import com.edti.exceltoxml.Services.Interfaces.IQuestionService;
import com.edti.exceltoxml.Services.Interfaces.IStateService;
import com.edti.exceltoxml.Services.Interfaces.IUploadService;
import com.edti.exceltoxml.Services.PathLocatorService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@PropertySource(value = {"/application.properties", "/global.properties"})
public class MainController {

    String filePath;

    @Value("${server.port}")
    private int port;

    private final IUploadService uploadService;
    private final PathLocatorService pathLocatorService;
    private final IStateService stateService;
    private final IQuestionService questionService;

    @Autowired
    public MainController(IUploadService uploadService,
                          PathLocatorService pathLocatorService,
                          IStateService stateService,
                          IQuestionService questionService) {
        this.uploadService = uploadService;
        this.pathLocatorService = pathLocatorService;
        this.stateService = stateService;
        this.questionService = questionService;
    }

    @RequestMapping("/")
    public String uploadFile() {
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
    public ModelAndView uploadResponse(Model m,
                                       @RequestParam("file") MultipartFile file,
                                       @Nullable @RequestParam("checkBox") String isPicture) throws IOException, URISyntaxException {
        if (isPicture == null || isPicture.equals("")) {
            System.out.println("off");
            stateService.setState(0);
        } else if (isPicture.equals("on")){
            System.out.println(isPicture);
            stateService.setState(1);
        } else {
            System.out.println("Ne huncutkodj!");
        }


        if (!file.getOriginalFilename().toLowerCase().endsWith(".xml") && !file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            throw new MissingFileException("Nem töltött fel fájlt!");
        }

        uploadService.handleExcelFile(file);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect");
        return mav;
    }



    @RequestMapping("/EDTI")
    public String EDTI() {
        return "EDTI";
    }

}
