package com.edti.exceltoxml.Models.Category;

import com.edti.exceltoxml.Models.Question.Text;

import javax.xml.bind.annotation.XmlAttribute;

public class Category extends Text {
    public Category() {
    }

    public Category(String text) {
        super(text);
    }
}
