package service;

import model.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;

public final class TaxCalculator {
    private static final BigDecimal BASIC_TAX_RATE = new BigDecimal("0.10");
    private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.05");
    private static final BigDecimal ROUNDING_INCREMENT = new BigDecimal("0.05");

    private TaxCalculator() {}

    public static BigDecimal calculateTax(Product product) {
        BigDecimal taxRate = BigDecimal.ZERO;

        if (!product.isTaxExempt()) {
            taxRate = taxRate.add(BASIC_TAX_RATE);
        }
        if (product.isImported()) {
            taxRate = taxRate.add(IMPORT_TAX_RATE);
        }

        BigDecimal rawTax = product.getPrice().multiply(taxRate);
        return roundTax(rawTax);
    }

    private static BigDecimal roundTax(BigDecimal tax) {
        BigDecimal divided = tax.divide(ROUNDING_INCREMENT, 0, RoundingMode.UP);
        return divided.multiply(ROUNDING_INCREMENT).setScale(2, RoundingMode.HALF_UP);
    }
}