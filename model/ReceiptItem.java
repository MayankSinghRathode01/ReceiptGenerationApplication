package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReceiptItem {
    private final Product product;
    private final int quantity;
    private final BigDecimal taxAmount;
    private final BigDecimal totalPrice;

    public ReceiptItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        this.product = product;
        this.quantity = quantity;
        this.taxAmount = product.calculateTax();
        this.totalPrice = product.getPrice().add(taxAmount)
                               .multiply(BigDecimal.valueOf(quantity))
                               .setScale(2, RoundingMode.HALF_UP);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return quantity + " " + product.getName() + ": " + totalPrice;
    }
}