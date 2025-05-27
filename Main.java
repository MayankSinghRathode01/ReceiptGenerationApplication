
import java.util.Scanner;

import model.Receipt;
import service.ReceiptBuilder;
import util.ReceiptPrinter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        
        System.out.println("Enter shopping basket items (one per line, empty line to finish):");
        System.out.println("Format: <quantity> <product name> at <price>");
        
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            try {
                receiptBuilder.addItem(line);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }
        
        Receipt receipt = receiptBuilder.build();
        ReceiptPrinter.printReceipt(receipt);
    }
}