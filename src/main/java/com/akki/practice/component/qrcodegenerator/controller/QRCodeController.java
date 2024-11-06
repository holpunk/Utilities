package com.akki.practice.component.qrcodegenerator.controller;

import com.akki.practice.component.qrcodegenerator.service.QRCodeGeneratorService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
public class QRCodeController {

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    @GetMapping(value = "/generate-qr", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateQRCode(@RequestParam String text) throws WriterException, IOException {
        String base64QRCode = qrCodeGeneratorService.generateQRCode(text, 300, 300);
        return Base64.getDecoder().decode(base64QRCode); // Convert Base64 back to byte array
    }
}
