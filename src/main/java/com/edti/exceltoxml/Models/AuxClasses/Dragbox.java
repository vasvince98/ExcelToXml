package com.edti.exceltoxml.Models.AuxClasses;

public class Dragbox extends AbstractAnswer {
    private String group;

    public Dragbox() {}

    public Dragbox(String text, String group) {
        setText(text);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
