package com.edti.exceltoxml.Models.Q.QuestionTypes;

import com.edti.exceltoxml.Models.Q.AuxClasses.Info;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class Cat extends Question {

    private Category category;
    private Info info;

    public Cat() {
    }

    public Cat(Category category, Info info) {
        this.category = category;
        this.info = info;
    }

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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "category=" + category +
                ", info=" + info +
                '}';
    }
}
