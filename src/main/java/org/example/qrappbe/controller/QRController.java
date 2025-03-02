package org.example.qrappbe.controller;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.example.qrappbe.service.QRService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/qr")
@CrossOrigin(origins = "http://localhost:4200")
public class QRController {
    private final QRService qrService;

    public QRController(QRService qrService) {
        this.qrService = qrService;
    }

    @GetMapping("/get")
    public String getQRCode(@RequestParam("text") String text) {
        byte[] image;

        try {
            image = qrService.getQRCode(text, 250,250);
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encodeToString(image);
    }
}
