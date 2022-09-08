package com.edti.exceltoxml.Models;

import java.util.ArrayList;
import java.util.List;

public class FormattedText {
    private int height;
    private int width;
    private List<String> lines;

    public FormattedText() {
        this.lines = new ArrayList<>();
    }

    public FormattedText(int height, int width) {
        this.height = height;
        this.width = width;
        this.lines = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "FormattedText{" +
                "height=" + height +
                ", width=" + width +
                ", lines=" + lines +
                '}';
    }
}
