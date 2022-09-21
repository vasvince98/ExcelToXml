package com.edti.exceltoxml;

import com.edti.exceltoxml.Models.Question.QuestionText;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println(new File("").getAbsolutePath());
    }

}
