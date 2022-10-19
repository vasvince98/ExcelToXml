package com.edti.exceltoxml.Services.Interfaces;

import org.apache.poi.ss.usermodel.Workbook;


import java.io.IOException;

public interface IQuestionService {
    String createXmlFromExcel(Workbook workbook) throws IOException;

}
