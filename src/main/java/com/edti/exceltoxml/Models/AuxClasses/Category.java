package com.edti.exceltoxml.Models.AuxClasses;

public class Category extends Auxiliary {
    private String text;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    public Category(String text) {
        setText(text);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if(!(obj instanceof Category category)) {
            return false;
        }

        return category.getText().equals(this.getText());
    }
}
