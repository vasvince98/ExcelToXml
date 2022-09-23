package com.edti.exceltoxml.Models.Q.QuestionTypes;

import com.edti.exceltoxml.Models.Q.AuxClasses.Info;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class Cat extends Question{

    private Category category;
    private Info info;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String getXmlForm() throws JAXBException, FileNotFoundException {
        return this.generateXmlForm(Cat.class, this.getClass().getSuperclass().getSimpleName().toLowerCase(), this);
    }
}
