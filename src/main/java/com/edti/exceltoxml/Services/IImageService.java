package com.edti.exceltoxml.Services;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface IImageService {
    BufferedImage renderStringToImage(String text);
    String imageToBase64(BufferedImage image) throws IOException;

}
