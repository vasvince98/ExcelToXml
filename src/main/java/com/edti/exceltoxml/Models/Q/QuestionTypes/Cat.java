package com.edti.exceltoxml.Models.Q.QuestionTypes;

import com.edti.exceltoxml.Models.Q.AuxClasses.Info;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlTransient;
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

    //region Getters and setters ######################################
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

    //endregion

    @Override
    public String getXmlForm() throws JAXBException, FileNotFoundException {
        return this.generateXmlForm(Cat.class, this.getClass().getSuperclass().getSimpleName().toLowerCase(), this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if(!(obj instanceof Cat category)) {
            return false;
        }

        return category.getCategory().equals(this.category) &&
                category.getInfo().equals(this.info);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "category=" + category +
                ", info=" + info +
                '}';
    }
}
