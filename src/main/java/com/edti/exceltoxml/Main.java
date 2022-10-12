package com.edti.exceltoxml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        SpringApplication.run(Main.class, args);
        System.out.println("Working directory: " + new File("").getAbsolutePath());
    }

}
