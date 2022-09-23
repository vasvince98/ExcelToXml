package com.edti.exceltoxml.Models.Q.QuestionTypes;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Truefalse extends RealQuestion{
    @Override
    public String getXmlForm() throws JAXBException, FileNotFoundException {
        return this.generateXmlForm(Truefalse.class, this.getClass().getSuperclass().getSuperclass().getSimpleName().toLowerCase(), this);
    }
}