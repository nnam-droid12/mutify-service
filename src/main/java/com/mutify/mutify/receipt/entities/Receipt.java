package com.mutify.mutify.receipt.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long businessId;
    private String customerName;

    @ElementCollection
    private List<String> items;

    private Double totalAmount;

    private LocalDateTime timestamp;

    public Receipt(Long id, Long businessId, String customerName, List<String> items, Double totalAmount, LocalDateTime timestamp) {
        this.id = id;
        this.businessId = businessId;
        this.customerName = customerName;
        this.items = items;
        this.totalAmount = totalAmount;
        this.timestamp = timestamp;
    }

    public Receipt() {
    }

    public static ReceiptBuilder builder() {
        return new ReceiptBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Long getBusinessId() {
        return this.businessId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public List<String> getItems() {
        return this.items;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static class ReceiptBuilder {
        private Long id;
        private Long businessId;
        private String customerName;
        private List<String> items;
        private Double totalAmount;
        private LocalDateTime timestamp;

        ReceiptBuilder() {
        }

        public ReceiptBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReceiptBuilder businessId(Long businessId) {
            this.businessId = businessId;
            return this;
        }

        public ReceiptBuilder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public ReceiptBuilder items(List<String> items) {
            this.items = items;
            return this;
        }

        public ReceiptBuilder totalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public ReceiptBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Receipt build() {
            return new Receipt(this.id, this.businessId, this.customerName, this.items, this.totalAmount, this.timestamp);
        }

        public String toString() {
            return "Receipt.ReceiptBuilder(id=" + this.id + ", businessId=" + this.businessId + ", customerName=" + this.customerName + ", items=" + this.items + ", totalAmount=" + this.totalAmount + ", timestamp=" + this.timestamp + ")";
        }
    }
}
