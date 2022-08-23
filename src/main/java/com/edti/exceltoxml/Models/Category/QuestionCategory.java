package com.edti.exceltoxml.Models.Category;

public class QuestionCategory {
    private Category category;
    private Info info;
    private String idNumber;

    public QuestionCategory() {}

    public QuestionCategory(Category category, Info info, String idNumber) {
        this.category = category;
        this.info = info;
        this.idNumber = idNumber;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
