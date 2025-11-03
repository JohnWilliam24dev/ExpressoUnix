package com.johnwilliam.ExpressoUnix.Controllers;

import com.google.zxing.WriterException;
import com.johnwilliam.ExpressoUnix.Configs.QrCode.DTO.PassagemDto;
import com.johnwilliam.ExpressoUnix.Configs.QrCode.Services.QrCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("qr-code")
public class QrCodeController {
    private final QrCodeService qrCodeService;

    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestBody PassagemDto passagemDto) {
        try {
            byte[] code = qrCodeService.generateQRCode(passagemDto.toString());
            return ResponseEntity.ok(code);
        } catch(WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "/decode", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> decodeQRCode(@RequestParam("file")MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {
            String decodedText = qrCodeService.decodeQRCode(file);
            response.put("passagem", decodedText);
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            response.put("error", "Fail when try decode QR code.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
