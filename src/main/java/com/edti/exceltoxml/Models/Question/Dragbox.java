package com.edti.exceltoxml.Models.Question;

public class Dragbox extends Text {
    private int group;

    public Dragbox() {
        this.group = 1;
    }

    public Dragbox(String text) {
        super(text);
        this.group = 1;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
