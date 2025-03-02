package org.example.qrappbe.controller;

import com.google.zxing.WriterException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.qrappbe.service.QRService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/qr")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class QRController {
    private final QRService qrService;

    @GetMapping("/get")
    public ResponseEntity<QRresponse> getQRCode(@RequestParam("text") String text) {
        byte[] image;

        try {
            image = qrService.getQRCode(text, 300,300);
            return ResponseEntity
                    .ok()
                    .body(new QRresponse(image));
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    public static class QRresponse {
        private byte[] qrCodeBase64;

        public QRresponse(byte[] qrCodeBase64) {
            this.qrCodeBase64 = qrCodeBase64;
        }
    }
}
