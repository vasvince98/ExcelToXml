package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Models.FormattedText;
import com.edti.exceltoxml.Models.GlobalProperties;
import com.edti.exceltoxml.Models.RenderAPI;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@PropertySource(value = "/global.properties")
public class ImageService implements IImageService {

    GlobalProperties globalProperties;

    @Autowired
    public ImageService(GlobalProperties globalProperties) {
        this.globalProperties = globalProperties;
    }

    /**
     *
     * @param text The text of the question or the answer you want to transform to picture
     * @return Base64 encoded string
     * @throws IOException thrown, because answers and questions are connected together,
     * and needed to be handled as a group
     */
    @Override
    public String transformStringToBase64(String text) throws IOException {
        FormattedText formattedText = getHeightAndWidth(text);

        RenderAPI renderer = new RenderAPI(formattedText.getWidth() * globalProperties.getCharacterWidthInPixels(),
                formattedText.getHeight() * globalProperties.getCharacterHeightInPixels());

        int heightOffset = globalProperties.getPictureMargin();
        for (String line : formattedText.getLines()) {
            renderer.addText(line, 0, heightOffset);
            heightOffset += globalProperties.getLinePadding();
        }
        return imageToBase64(renderer.getRenderedImage());
    }


    private String imageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);

        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    //todo: Create comments

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
