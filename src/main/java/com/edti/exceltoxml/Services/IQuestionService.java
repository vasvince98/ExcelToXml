package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Models.Question.File;
import com.edti.exceltoxml.Models.Quiz;
import org.apache.poi.ss.usermodel.Workbook;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface IQuestionService {
    Quiz createObjectFromExcel(Workbook workbook) throws IOException;
    String createXmlFromObject(Quiz quiz) throws JAXBException;

    String createImageXmlFromStringXml(java.io.File inputXml) throws IOException, JAXBException;

}
