package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Exceptions.MissingDataException;
import com.edti.exceltoxml.Exceptions.OwnError;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class ExceptionHandlerController implements ErrorController {

    @ExceptionHandler({MissingDataException.class})
    public String emptyCellException(Model m) {
        OwnError error = new OwnError("Every cell must be filled!");
        m.addAttribute("error", error);
        return "error";
    }
}
