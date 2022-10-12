package com.edti.exceltoxml.Models.AuxClasses;

public class Category extends Auxiliary {

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
