package com.edti.exceltoxml.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/upload")
    public String uploadFile() {
        return "index";
    }
}
