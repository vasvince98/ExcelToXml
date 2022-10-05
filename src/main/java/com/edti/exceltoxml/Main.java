package com.edti.exceltoxml;

import com.edti.exceltoxml.Exceptions.MissingFileException;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import com.edti.exceltoxml.Services.PathLocatorService;
import com.edti.exceltoxml.Services.QuestionObjectProviders.MultichoiceQuestionProvider;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        SpringApplication.run(Main.class, args);
        System.out.println("Working directory: " + new File("").getAbsolutePath());
    }

}
