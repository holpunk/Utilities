package com.akki.utilities.service;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ImageConverterService {

    public byte[] convertImageToFormat(File inputFile, String formatName) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputFile);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, formatName, outputStream);
        return outputStream.toByteArray();
    }
}