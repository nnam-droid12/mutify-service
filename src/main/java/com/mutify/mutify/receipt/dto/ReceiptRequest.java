package com.mutify.mutify.receipt.dto;


import java.util.List;

public class ReceiptRequest {
    private Long businessId;
    private String customerName;
    private List<String> items;
    private Double totalAmount;

    public ReceiptRequest() {
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReceiptRequest)) return false;
        final ReceiptRequest other = (ReceiptRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$businessId = this.getBusinessId();
        final Object other$businessId = other.getBusinessId();
        if (this$businessId == null ? other$businessId != null : !this$businessId.equals(other$businessId))
            return false;
        final Object this$customerName = this.getCustomerName();
        final Object other$customerName = other.getCustomerName();
        if (this$customerName == null ? other$customerName != null : !this$customerName.equals(other$customerName))
            return false;
        final Object this$items = this.getItems();
        final Object other$items = other.getItems();
        if (this$items == null ? other$items != null : !this$items.equals(other$items)) return false;
        final Object this$totalAmount = this.getTotalAmount();
        final Object other$totalAmount = other.getTotalAmount();
        if (this$totalAmount == null ? other$totalAmount != null : !this$totalAmount.equals(other$totalAmount))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReceiptRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $businessId = this.getBusinessId();
        result = result * PRIME + ($businessId == null ? 43 : $businessId.hashCode());
        final Object $customerName = this.getCustomerName();
        result = result * PRIME + ($customerName == null ? 43 : $customerName.hashCode());
        final Object $items = this.getItems();
        result = result * PRIME + ($items == null ? 43 : $items.hashCode());
        final Object $totalAmount = this.getTotalAmount();
        result = result * PRIME + ($totalAmount == null ? 43 : $totalAmount.hashCode());
        return result;
    }

    public String toString() {
        return "ReceiptRequest(businessId=" + this.getBusinessId() + ", customerName=" + this.getCustomerName() + ", items=" + this.getItems() + ", totalAmount=" + this.getTotalAmount() + ")";
    }
}
