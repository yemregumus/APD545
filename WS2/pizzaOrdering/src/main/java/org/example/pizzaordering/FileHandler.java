package org.example.pizzaordering;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileHandler {

    // Method to save order and customer details to a text file
    public static void saveToFile(Order order) {
        String fileName = "order_details.txt"; // Name of the text file
        String filePath = "src/" + fileName; // Path to the text file

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            // Generate a random order number (e.g., 6-digit number)
            int orderNumber = generateOrderNumber();
            // Write order number
            writer.write("Order Number: " + orderNumber);
            writer.newLine();
            // Write customer details
            writer.write("Customer Name: " + order.getCustomer().getName());
            writer.newLine();
            writer.write("Customer Number: " + order.getCustomer().getPhoneNumber());
            writer.newLine();

            // Write order details
            writer.write("Pizza Quantity: " + order.getQuantity());
            writer.newLine();
            writer.write("Pizza Size: " + order.getPizzaSize());
            writer.newLine();
            writer.write("Crust Type: " + order.getCrustType());
            writer.newLine();
            writer.write("Total Price (Tax Included): " + String.format("$%.2f", order.getTotalPrice()));
            writer.newLine();
            writer.write("Toppings: " + String.join(", ", order.getToppings()));
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the IOException (e.g., log error or show an alert)
        }
    }

    // Method to generate a random 6-digit order number
    private static int generateOrderNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // Generates a random number between 100000 and 999999
    }
}
