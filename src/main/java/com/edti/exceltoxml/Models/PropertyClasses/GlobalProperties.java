package com.edti.exceltoxml.Models.PropertyClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = "/global.properties")
@Component
public class GlobalProperties {
    private int linePadding;
    private int pictureMargin;
    private int characterWidthInPixels;
    private int characterHeightInPixels;

    public GlobalProperties() {}

    @Autowired
    public GlobalProperties(@Value("${linePadding}") int linePadding,
                            @Value("${pictureMargin}") int pictureMargin,
                            @Value("${characterWidthInPixels}") int characterWidthInPixels,
                            @Value("${characterHeightInPixels}") int characterHeightInPixels) {
        this.linePadding = linePadding;
        this.pictureMargin = pictureMargin;
        this.characterHeightInPixels = characterHeightInPixels;
        this.characterWidthInPixels = characterWidthInPixels;
    }

    public int getLinePadding() {
        return linePadding;
    }

    public void setLinePadding(int linePadding) {
        this.linePadding = linePadding;
    }

    public int getPictureMargin() {
        return pictureMargin;
    }

    public void setPictureMargin(int pictureMargin) {
        this.pictureMargin = pictureMargin;
    }

    public int getCharacterWidthInPixels() {
        return characterWidthInPixels;
    }

    public void setCharacterWidthInPixels(int characterWidthInPixels) {
        this.characterWidthInPixels = characterWidthInPixels;
    }

    public int getCharacterHeightInPixels() {
        return characterHeightInPixels;
    }

    public void setCharacterHeightInPixels(int characterHeightInPixels) {
        this.characterHeightInPixels = characterHeightInPixels;
    }
}
