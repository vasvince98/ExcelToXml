package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Models.FormattedText;
import com.edti.exceltoxml.Models.RenderAPI;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {

    @Override
    public BufferedImage renderStringToImage(String text) {
        FormattedText formattedText = getHeightAndWidth(text);
        RenderAPI renderer = new RenderAPI(formattedText.getWidth() * 7, formattedText.getHeight() * 25);

        formattedText.getLines().forEach(System.out::println);

        int y = 10;
        for (String line : formattedText.getLines()) {
            renderer.addText(line, 0, y);
            y += 20;
        }
        return renderer.getRenderedImage();
    }

    @Override
    public String imageToBase64(BufferedImage image) {
        return null;
    }

    private FormattedText getHeightAndWidth(String text) {
        FormattedText formattedTextClass = new FormattedText();
        int lineBreak = 0;
        int longestLine = 0;
        int i = 0;
        List<String> lines = new ArrayList<>();
        String line = "";

        String[] parts = text.split(" ");

        for (String part : parts) {
            if (i > 5) {
                longestLine = line.length();
                lineBreak++;
                lines.add(line);
                i = 0;
                line = "";
            }
            line = line.concat(part + " ");
            i++;
        }
        lines.add(line);
        formattedTextClass.setLines(lines);
        formattedTextClass.setWidth(longestLine);
        formattedTextClass.setHeight(lineBreak);

        return formattedTextClass;
    }
}
