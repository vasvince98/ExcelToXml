package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Models.Quiz;

import java.io.File;

public interface IQuestionService {
    Quiz createObjectFromExcel(File file);
    String createXmlFromObject(Quiz quiz);

}
