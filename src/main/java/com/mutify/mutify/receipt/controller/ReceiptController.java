package com.mutify.mutify.receipt.controller;


import com.mutify.mutify.receipt.dto.ReceiptRequest;
import com.mutify.mutify.receipt.entities.Receipt;
import com.mutify.mutify.receipt.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/create-receipt")
    public ResponseEntity<Receipt> createReceipt(@RequestBody ReceiptRequest request) {
        Receipt savedReceipt = receiptService.createReceipt(request);
        return ResponseEntity.ok(savedReceipt);
    }

    @GetMapping("/get-receiptById/{id}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable Long id) {
        Optional<Receipt> receipt = receiptService.getReceiptById(id);
        return receipt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get-receiptByBusinessId/{businessId}")
    public ResponseEntity<List<Receipt>> getReceiptsByBusinessId(@PathVariable Long businessId) {
        List<Receipt> receipts = receiptService.getReceiptsByBusinessId(businessId);
        return ResponseEntity.ok(receipts);
    }
}

