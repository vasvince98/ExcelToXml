package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Models.FormattedText;
import com.edti.exceltoxml.Models.RenderAPI;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImageService implements IImageService {

    @Override
    public BufferedImage renderStringToImage(String text) {
        System.out.println(text);
        FormattedText formattedText = getHeightAndWidth(text);
        RenderAPI renderer = new RenderAPI(formattedText.getWidth() * 7, formattedText.getHeight() * 30);

        int y = 15;
        for (String line : formattedText.getLines()) {
            renderer.addText(line, 0, y);
            y += 20;
        }
        return renderer.getRenderedImage();
    }

    @Override
    public String imageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);

        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    private FormattedText getHeightAndWidth(String text) {
        FormattedText formattedTextClass = new FormattedText();
        int lineBreak = 1;
        int longestLine = 0;
        int i = 0;
        List<String> lines = new ArrayList<>();
        String line = "";

        String[] parts = text.split(" ");

        for (String part : parts) {
            if (i > 9) {
                lineBreak++;
                lines.add(line);
                i = 0;
                line = "";
            }
            line = line.concat(part + " ");
            if (line.length() > longestLine) {
                longestLine = line.length();
            }
            i++;
        }
        lines.add(line);
        formattedTextClass.setLines(lines);
        formattedTextClass.setWidth(longestLine);
        formattedTextClass.setHeight(lineBreak);

        return formattedTextClass;
    }
}
