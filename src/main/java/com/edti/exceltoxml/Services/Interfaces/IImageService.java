package com.edti.exceltoxml.Services.Interfaces;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface IImageService {
    BufferedImage renderStringToImage(String text);
    String imageToBase64(BufferedImage image) throws IOException;

}
