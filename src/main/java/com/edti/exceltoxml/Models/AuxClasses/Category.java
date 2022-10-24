package com.edti.exceltoxml.Models.AuxClasses;

public class Category extends Auxiliary {
    private String text;

    @Override
    public String getText() {
        return this.text;
    }



    public Category(String text) {
        setImageText(text);
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
