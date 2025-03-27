package com.mutify.mutify.speech.service;

import com.mutify.mutify.receipt.entities.Receipt;
import com.mutify.mutify.receipt.service.ReceiptService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        } else if (userInput.contains("receipt with highest amount")) {
            return getReceiptWithHighestAmount();
        } else if (userInput.contains("all receipts")) {
            return getAllReceipts();
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

    private String getReceiptWithHighestAmount() {
        Optional<Receipt> highestReceipt = receiptService.getReceiptWithHighestAmount();

        if (highestReceipt.isPresent()) {
            Receipt receipt = highestReceipt.get();
            return "Receipt with highest amount:\n" +
                    "ID: " + receipt.getId() +
                    "\nAmount: $" + receipt.getTotalAmount() +
                    "\nDate: " + receipt.getTimestamp() +
                    "\nCustomer: " + receipt.getCustomerName();
        }

        return "No receipts found.";
    }

    private String getAllReceipts() {
        List<Receipt> allReceipts = receiptService.getAllReceipts();

        if (allReceipts.isEmpty()) {
            return "No receipts found.";
        }

        StringBuilder response = new StringBuilder("All Receipts:\n");
        for (Receipt receipt : allReceipts) {
            response.append("ID: ").append(receipt.getId())
                    .append(", Amount: $").append(receipt.getTotalAmount())
                    .append(", Date: ").append(receipt.getTimestamp())
                    .append(", Customer: ").append(receipt.getCustomerName())
                    .append("\n");
        }
        return response.toString();
    }
}