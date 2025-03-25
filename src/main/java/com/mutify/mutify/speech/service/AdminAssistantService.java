package com.mutify.mutify.speech.service;

import com.mutify.mutify.receipt.entities.Receipt;
import com.mutify.mutify.receipt.service.ReceiptService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminAssistantService {


    private final ReceiptService receiptService;

    public AdminAssistantService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public String processCommand(String userInput) {
        userInput = userInput.toLowerCase();

        if (userInput.contains("last week's receipt") || userInput.contains("last week transactions")) {
            return getLastWeekReceipts();
        } else if (userInput.contains("total transactions today")) {
            return getTotalTransactionsToday();
        } else {
            return "Sorry, I didn't understand your request.";
        }
    }

    private String getLastWeekReceipts() {
        LocalDate today = LocalDate.now();
        LocalDate lastWeek = today.minusDays(7);

        List<Receipt> receipts = receiptService.getReceiptsByDateRange(lastWeek.atStartOfDay(), today.atTime(23, 59));

        if (receipts.isEmpty()) {
            return "No receipts found from last week.";
        }

        StringBuilder response = new StringBuilder("Here are the transactions from last week:\n");
        for (Receipt receipt : receipts) {
            response.append("ID: ").append(receipt.getId())
                    .append(", Amount: $").append(receipt.getTotalAmount())
                    .append(", Date: ").append(receipt.getTimestamp())
                    .append("\n");
        }
        return response.toString();
    }

    private String getTotalTransactionsToday() {
        LocalDate today = LocalDate.now();

        List<Receipt> todayReceipts = receiptService.getReceiptsByDateRange(today.atStartOfDay(), today.atTime(23, 59));

        int count = todayReceipts.size();
        double totalAmount = todayReceipts.stream().mapToDouble(Receipt::getTotalAmount).sum();

        return "Total transactions today: " + count + " | Total Amount: $" + totalAmount;
    }
}

