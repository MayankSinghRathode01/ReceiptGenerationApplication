package util;

import model.Receipt;

public final class ReceiptPrinter {
    private ReceiptPrinter() {}

    public static void printReceipt(Receipt receipt) {
        System.out.println("\nReceipt:");
        receipt.getItems().forEach(System.out::println);
        System.out.println("Sales Taxes: " + receipt.getTotalTax());
        System.out.println("Total: " + receipt.getTotalAmount());
    }
}