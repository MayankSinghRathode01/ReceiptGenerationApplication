package service;

import model.Product;
import model.ReceiptItem;
import java.math.BigDecimal;

public final class LineItemParser {
    private LineItemParser() {}

    public static ReceiptItem parse(String inputLine) {
        if (inputLine == null || inputLine.isBlank()) {
            throw new IllegalArgumentException("Input line cannot be empty");
        }

        String[] priceParts = inputLine.split(" at ");
        if (priceParts.length != 2) {
            throw new IllegalArgumentException("Invalid price format. Expected format: '... at <price>'");
        }

        BigDecimal price = parsePrice(priceParts[1].trim());
        String quantityAndName = priceParts[0].trim();

        int spaceIndex = quantityAndName.indexOf(' ');
        if (spaceIndex <= 0) {
            throw new IllegalArgumentException("Invalid quantity and product name format");
        }

        int quantity = parseQuantity(quantityAndName.substring(0, spaceIndex));
        String name = quantityAndName.substring(spaceIndex + 1).trim();
        
        boolean imported = name.toLowerCase().contains("imported");
        Product product = new Product(name, price, imported);

        return new ReceiptItem(product, quantity);
    }

    private static int parseQuantity(String quantityStr) {
        try {
            int quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }
            return quantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid quantity: " + quantityStr);
        }
    }

    private static BigDecimal parsePrice(String priceStr) {
        try {
            BigDecimal price = new BigDecimal(priceStr);
            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Price must be positive");
            }
            return price;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price: " + priceStr);
        }
    }
}