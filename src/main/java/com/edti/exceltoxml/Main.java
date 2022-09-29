package com.edti.exceltoxml;

import com.edti.exceltoxml.Exceptions.MissingFileException;
import com.edti.exceltoxml.Services.PathLocatorService;
import com.edti.exceltoxml.Services.QuestionObjectProviders.MultichoiceQuestionProvider;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Working directory: " + new File("").getAbsolutePath());

        MultichoiceQuestionProvider multichoiceProvider = new MultichoiceQuestionProvider();
        multichoiceProvider.objectListFromSheet(excelTest());

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
