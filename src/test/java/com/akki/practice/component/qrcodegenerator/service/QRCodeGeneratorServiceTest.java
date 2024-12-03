package com.akki.practice.component.qrcodegenerator.service;

import com.google.zxing.WriterException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class QRCodeGeneratorServiceTest {

    private final QRCodeGeneratorService qrCodeGeneratorService = new QRCodeGeneratorService();

    @Test
     void testGenerateQRCode() throws WriterException, IOException {
        String text = "Hello, World!";
        int width = 200;
        int height = 200;

        String qrCode = qrCodeGeneratorService.generateQRCode(text, width, height);
        assertNotNull(qrCode, "QR Code should not be null");
    }

    @Test
     void testGenerateQRCodeWithInvalidDimensions() {
        String text = "Hello, World!";
        int width = -1;
        int height = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            qrCodeGeneratorService.generateQRCode(text, width, height);
        });
    }
}