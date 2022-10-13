package com.edti.exceltoxml;

import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.PropertyClasses.GlobalProperties;
import com.edti.exceltoxml.Models.QuestionTypes.Multichoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws FileNotFoundException, JAXBException {

        SpringApplication.run(Main.class, args);
        System.out.println("Working directory: " + new File("").getAbsolutePath());

    }

}
