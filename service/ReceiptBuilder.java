package service;

import model.Receipt;
import model.ReceiptItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ReceiptBuilder {
    private final List<ReceiptItem> items = new ArrayList<>();

    public void addItem(String inputLine) {
        ReceiptItem item = LineItemParser.parse(inputLine);
        items.add(item);
    }

    public Receipt build() {
        BigDecimal totalTax = items.stream()
            .map(ReceiptItem::getTaxAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .setScale(2, RoundingMode.HALF_UP);

        BigDecimal totalAmount = items.stream()
            .map(ReceiptItem::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .setScale(2, RoundingMode.HALF_UP);

        return new Receipt(items, totalTax, totalAmount);
    }
}