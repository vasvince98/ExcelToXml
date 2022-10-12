package com.edti.exceltoxml;

import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.QuestionTypes.Multichoice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws FileNotFoundException, JAXBException {





        SpringApplication.run(Main.class, args);
        System.out.println("Working directory: " + new File("").getAbsolutePath());

        Multichoice m = (Multichoice) QuestionFactory.getQuestion(QType.multichoice, SimulateDataLoadingProcess());

        System.out.println(m.getXmlForm());

    }
    private static HashMap<String, String> SimulateDataLoadingProcess(){
        HashMap<String, String> tmp = new HashMap<>();
        tmp.put("name", "kérdés neve");
        tmp.put("questiontext", "a kérdés szövege");
        tmp.put("generalfeedback", "ügyes vagy!");
        tmp.put("defaultgrade", "1.0000000");
        tmp.put("penalty", "0.3333333");
        tmp.put("hidden", "0");
        tmp.put("idnumber", "");
        tmp.put("single", "false");
        tmp.put("shuffleanswers", "true");
        tmp.put("answernumbering", "abc");
        tmp.put("showstandardinstruction", "0");
        tmp.put("correctfeedback", "Válasza helyes.");
        tmp.put("partiallycorrectfeedback", "Válasza részben helyes.");
        tmp.put("incorrectfeedback", "Válasza helytelen.");
        tmp.put("shownumcorrect", "");
        return tmp;
    }

}
