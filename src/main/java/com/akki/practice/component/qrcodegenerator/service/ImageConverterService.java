package com.akki.practice.component.qrcodegenerator.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageConverterService {

    public File convertToJpg(InputStream inputStream, String outputFilePath) throws IOException {
        // Read the image from the input stream
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Create a new file for the JPG output
        File outputFile = new File(outputFilePath);

        // Write the image as a JPG file
        ImageIO.write(originalImage, "jpg", outputFile);

        return outputFile;
    }
}
