package com.edti.exceltoxml.Controllers;

import com.edti.exceltoxml.Exceptions.*;
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

    @ExceptionHandler({MissingDataException.class})
    public String incorrectFieldException(Model m) {
        OwnError error = new OwnError("Every cell must be filled!");
        m.addAttribute("error", error);
        return "error";
    }



    @ExceptionHandler({MissingHeaderException.class})
    public String missingHeaderExceptionHandler(Model m) {
        OwnError error = new OwnError("Az első sorban szerepelnie kell a mező típusoknak!");
        m.addAttribute("error", error);
        return "error";
    }

    @ExceptionHandler({NullAnswerException.class})
    public String nullAnswerExceptionHandler(Model m) {
        OwnError error = new OwnError("Nem vont le pontot a rossz válaszért! Biztosan beküldi a tesztet?");
        m.addAttribute("error", error);
        return "error";
    }

    @ExceptionHandler({MissingFileException.class})
    public String missingFileExceptionHandler(Model m) {
        OwnError error = new OwnError("Nem töltött fel fájlt!");
        m.addAttribute("error", error);
        return "error";
    }

    @ExceptionHandler({QuestionNotExistException.class})
    public String QuestionNotExistExceptionHandler(Model m) {
        OwnError error = new OwnError("Hibás kérdéstípus! Nézd meg ");
        m.addAttribute("error", error);
        return "error";
    }
}
