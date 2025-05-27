package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import service.TaxCalculator;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final boolean imported;
    private final boolean taxExempt;
    private static final Set<String> EXEMPT_KEYWORDS = Set.of("book", "chocolate", "chocolates", "pill", "pills");

    public Product(String name, BigDecimal price, boolean imported) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        
        this.name = name;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.imported = imported;
        this.taxExempt = EXEMPT_KEYWORDS.stream().anyMatch(name.toLowerCase()::contains);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isImported() {
        return imported;
    }

    public boolean isTaxExempt() {
        return taxExempt;
    }

    public BigDecimal calculateTax() {
        return TaxCalculator.calculateTax(this);
    }
}