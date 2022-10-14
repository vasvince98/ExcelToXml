package com.edti.exceltoxml.Models.QuestionTypes;

import com.edti.exceltoxml.Models.PropertyClasses.FieldProperties;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlTransient;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Ddwtos extends RealQuestion {

    @XmlTransient
    private final FieldProperties fieldProperties;

    public Ddwtos(HashMap<String, String> data, FieldProperties fieldProperties) {
        this.fieldProperties = fieldProperties;
        initInstance(data);
    }

    @Override
    public String getXmlForm() throws JAXBException, FileNotFoundException {
        return null;
    }

    @Override
    protected void initInstance(HashMap<String, String> data) {

    }
}
