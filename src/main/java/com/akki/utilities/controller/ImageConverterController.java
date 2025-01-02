package com.akki.utilities.controller;

import com.akki.utilities.service.ImageConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/image-converter")
public class ImageConverterController {

    @Autowired
    private ImageConverterService imageConverterService;

    @PostMapping("/convert")
    public ResponseEntity<byte[]> convertImage(@RequestParam("file") MultipartFile file,
                                               @RequestParam("format") String format) {
        try {
            File inputFile = File.createTempFile("input", null);
            file.transferTo(inputFile);
            byte[] convertedImage = imageConverterService.convertImageToFormat(inputFile, format);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/" + format);
            headers.add("Content-Disposition", "attachment; filename=converted." + format);
            return new ResponseEntity<>(convertedImage, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}