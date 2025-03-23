package com.mutify.mutify.receipt.service;

import com.mutify.mutify.receipt.dto.ReceiptRequest;
import com.mutify.mutify.receipt.entities.Receipt;
import com.mutify.mutify.receipt.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public Receipt createReceipt(ReceiptRequest request) {
        Receipt receipt = Receipt.builder()
                .businessId(request.getBusinessId())
                .customerName(request.getCustomerName())
                .items(request.getItems())
                .totalAmount(request.getTotalAmount())
                .timestamp(LocalDateTime.now())
                .build();

        return receiptRepository.save(receipt);
    }

    public Optional<Receipt> getReceiptById(Long id) {
        return receiptRepository.findById(id);
    }

    public List<Receipt> getReceiptsByBusinessId(Long businessId) {
        return receiptRepository.findByBusinessId(businessId);
    }
}
