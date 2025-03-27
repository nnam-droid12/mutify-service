package com.mutify.mutify.receipt.controller;


import com.mutify.mutify.receipt.service.ReceiptImageService;
import com.mutify.mutify.receipt.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/receipts")
public class ReceiptImageController {

    private final ReceiptService receiptService;
    private final ReceiptImageService receiptImageService;

    public ReceiptImageController(ReceiptService receiptService, ReceiptImageService receiptImageService) {
        this.receiptService = receiptService;
        this.receiptImageService = receiptImageService;
    }

    @PostMapping("/image")
    public ResponseEntity<String> generateReceiptImage(@RequestBody String jsonReceipt) {
        try {
            String imageUrl = receiptImageService.generateAndUploadReceiptImage(jsonReceipt);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error generating receipt image");
        }
    }

}

