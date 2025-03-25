package com.mutify.mutify.receipt.controller;

import com.mutify.mutify.receipt.entities.Receipt;
import com.mutify.mutify.receipt.service.QrCodeService;
import com.mutify.mutify.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/receipts")
public class QrCodeController {


    private final QrCodeService qrCodeService;


    private final ReceiptService receiptService;

    public QrCodeController(QrCodeService qrCodeService, ReceiptService receiptService) {
        this.qrCodeService = qrCodeService;
        this.receiptService = receiptService;
    }

    @GetMapping("/generate-qr/{receiptId}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable String receiptId) {
        String qrContent = "http://localhost:5050/receipt/" + receiptId;
        byte[] qrImage = qrCodeService.generateQRCode(qrContent, 300, 300);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrImage);
    }

    @GetMapping("/get-receiptByQrCode/{id}")
    public ResponseEntity<Receipt> getReceiptByQRCode(@PathVariable Long id) {
        Optional<Receipt> receipt = receiptService.getReceiptById(id);
        return receipt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

