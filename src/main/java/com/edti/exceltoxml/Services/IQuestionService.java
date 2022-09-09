package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Models.Quiz;
import org.apache.poi.ss.usermodel.Workbook;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public interface IQuestionService {
    Quiz createObjectFromExcel(Workbook workbook) throws IOException;
    String createXmlFromObject(Quiz quiz) throws JAXBException;

}
