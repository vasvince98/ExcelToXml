package com.edti.exceltoxml.Services;

import java.awt.image.BufferedImage;

public interface IImageService {
    BufferedImage renderStringToImage(String text);
    String imageToBase64(BufferedImage image);

}
