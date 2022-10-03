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
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Working directory: " + new File("").getAbsolutePath());

        MultichoiceQuestionProvider multichoiceProvider = new MultichoiceQuestionProvider();
        Map<Cat, List<RealQuestion>> map = multichoiceProvider.objectListFromSheet(excelTest());




        System.out.println("////////////////////////////////////////////////////////////////////////");



        map.forEach(((cat, realQuestions) -> {
            try {
                System.out.println("Category xml: ");
                System.out.println(cat.getXmlForm());
                System.out.println("Question xml: ");
                realQuestions.forEach((realQuestion -> {
                    try {
                        System.out.println(realQuestion.getXmlForm());
                    } catch (JAXBException | FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }));
            } catch (JAXBException | FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }));

    }

    private static Sheet excelTest() {
        try {
            String filePath = "";
            PathLocatorService pathLocatorService = new PathLocatorService();
            File folder = new File(pathLocatorService.getPath());
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            String fileName = null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.getName().toLowerCase().endsWith(".xlsx")) {
                    filePath = listOfFile.getPath();
                    fileName = listOfFile.getName();
                }
            }

            File localSaveFile = new File(filePath);

            Workbook wb = WorkbookFactory.create(localSaveFile);

            return wb.getSheetAt(0);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MissingFileException("Nem töltött fel fájlt!");
        }
    }

}
