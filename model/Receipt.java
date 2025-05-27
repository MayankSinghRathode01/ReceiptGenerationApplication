package model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public final class Receipt {
    private final List<ReceiptItem> items;
    private final BigDecimal totalTax;
    private final BigDecimal totalAmount;

    public Receipt(List<ReceiptItem> items, BigDecimal totalTax, BigDecimal totalAmount) {
        this.items = Collections.unmodifiableList(items);
        this.totalTax = totalTax;
        this.totalAmount = totalAmount;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}