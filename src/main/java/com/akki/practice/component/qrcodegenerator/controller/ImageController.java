package com.akki.practice.component.qrcodegenerator.controller;

import com.akki.practice.component.qrcodegenerator.service.ImageConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageConverterService imageConverterService;

    @PostMapping("/convert-to-jpg")
    public ResponseEntity<byte[]> convertImageToJpg(@RequestParam("image") MultipartFile image) {
        try {
            // Generate a unique output file name
            String outputFilePath = "output/" + System.currentTimeMillis() + ".jpg";

            // Convert the image to JPG and retrieve it as a byte array
            File convertedImage = imageConverterService.convertToJpg(image.getInputStream(), outputFilePath);

            // Read the converted image file into a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try (FileInputStream inputStream = new FileInputStream(convertedImage)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            byte[] imageBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "image/jpeg");
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
