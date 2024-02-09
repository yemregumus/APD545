/**********************************************
 Workshop #
 Course: APD545
 Semester: 5th Semester
 Last Name: Gumus
 First Name: Yunus Emre
 ID: 150331197
 Section: NAA

 This assignment represents my own work in accordance with Seneca Academic Policy.

 Signature: Y.E.G.
 Date: 12/02/2024
 **********************************************/

package org.example.pizzaordering;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    // Injecting FXML elements
    @FXML
    private TextField customerNameTextField;

    @FXML
    private TextField customerNumberTextField;

    @FXML
    private TextField pizzaQuantityTextField;

    @FXML
    private ChoiceBox<String> pizzaSizeChoiceBox;

    @FXML
    private ChoiceBox<String> crustChoiceBox;


    @FXML
    private CheckBox pineappleCheckBox;

    @FXML
    private CheckBox extraCheeseCheckBox;

    @FXML
    private CheckBox driedShrimpsCheckBox;

    @FXML
    private CheckBox mushroomsCheckBox;

    @FXML
    private CheckBox anchoviesCheckBox;

    @FXML
    private CheckBox sunDriedTomatoesCheckBox;

    @FXML
    private CheckBox daconCheckBox;
    @FXML
    private CheckBox spinachCheckBox;
    @FXML
    private CheckBox jalapenoCheckBox;

    @FXML
    private CheckBox roastedGarlicCheckBox;

    @FXML
    private CheckBox groundBeefCheckBox;

    @FXML
    private CheckBox shreddedChickenCheckBox;
    @FXML
    private CheckBox grilledChickenCheckBox;

    @FXML
    private CheckBox pepperoniCheckBox;
    @FXML
    private CheckBox hamCheckBox;

    @FXML
    private CheckBox baconCheckBox;

    @FXML
    private final List<CheckBox> normalToppingsCheckBoxes = new ArrayList<>();

    @FXML
    private final List<CheckBox> meatToppingsCheckBoxes = new ArrayList<>();

    // Injecting other UI elements
    @FXML
    private Button clearFieldsButton;

    @FXML
    private Button placeOrderButton;

    @FXML
    public void initialize() {
        // Populate the pizza size choice box
        pizzaSizeChoiceBox.getItems().addAll("Small - $7.00", "Medium - $10.00", "Large - $13.00", "Extra Large - $15.00");

        // Populate the crust choice box
        crustChoiceBox.getItems().addAll("Normal", "Thin", "Deep Dish (+$2.00)");

        // Add normal toppings checkboxes to the list
        normalToppingsCheckBoxes.add(pineappleCheckBox);
        normalToppingsCheckBoxes.add(extraCheeseCheckBox);
        normalToppingsCheckBoxes.add(driedShrimpsCheckBox);
        normalToppingsCheckBoxes.add(mushroomsCheckBox);
        normalToppingsCheckBoxes.add(anchoviesCheckBox);
        normalToppingsCheckBoxes.add(sunDriedTomatoesCheckBox);
        normalToppingsCheckBoxes.add(daconCheckBox);
        normalToppingsCheckBoxes.add(spinachCheckBox);
        normalToppingsCheckBoxes.add(roastedGarlicCheckBox);
        normalToppingsCheckBoxes.add(jalapenoCheckBox);

        // Add meat toppings checkboxes to the list
        meatToppingsCheckBoxes.add(groundBeefCheckBox);
        meatToppingsCheckBoxes.add(shreddedChickenCheckBox);
        meatToppingsCheckBoxes.add(grilledChickenCheckBox);
        meatToppingsCheckBoxes.add(pepperoniCheckBox);
        meatToppingsCheckBoxes.add(hamCheckBox);
        meatToppingsCheckBoxes.add(baconCheckBox);
    }

    @FXML
    private void handleClearFields() {
        // Clear all the text fields and checkboxes
        customerNameTextField.clear();
        customerNumberTextField.clear();
        pizzaQuantityTextField.clear();
        pizzaSizeChoiceBox.getSelectionModel().clearSelection();
        crustChoiceBox.getSelectionModel().clearSelection();

        // Clear normal toppings checkboxes
        clearCheckboxes(normalToppingsCheckBoxes);

        // Clear meat toppings checkboxes
        clearCheckboxes(meatToppingsCheckBoxes);
    }

    @FXML
    private void handlePlaceOrder() {
        String customerName = customerNameTextField.getText();
        if (!customerName.matches("[a-zA-Z ]+")) {
            showError("Customer name should only contain letters.");
            return;
        }

        String customerNumber = customerNumberTextField.getText();
        if (!customerNumber.matches("\\d+")) {
            showError("Customer number should only contain numbers.");
            return;
        }

        int pizzaQuantity;
        try {
            pizzaQuantity = Integer.parseInt(pizzaQuantityTextField.getText());
            if (pizzaQuantity > 50) {
                showError("You can't order more than 50 pizzas.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid number for pizza quantity.");
            return;
        }
        String pizzaSize = pizzaSizeChoiceBox.getValue();
        String crustType = crustChoiceBox.getValue();

        // Calculate base price based on pizza size
        double basePrice = calculateBasePrice(pizzaSize);

        // Calculate additional charges for toppings
        double normalToppingsPrice = calculateToppingsPrice(normalToppingsCheckBoxes);
        double meatToppingsPrice = calculateToppingsPrice(meatToppingsCheckBoxes);

        // Calculate additional charges for crust type
        double crustPrice = calculateCrustPrice(crustType);

        // Calculate total price including 13 percent tax
        double totalPrice = (basePrice + normalToppingsPrice + meatToppingsPrice + crustPrice) * pizzaQuantity * 1.13;

        // Calculate order details
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Customer Name: ").append(customerName).append("\n");
        orderDetails.append("Customer Number: ").append(customerNumber).append("\n");
        orderDetails.append("Pizza Quantity: ").append(pizzaQuantity).append("\n");
        orderDetails.append("Pizza Size: ").append(pizzaSize).append("\n");
        orderDetails.append("Crust Type: ").append(crustType).append("\n");
        orderDetails.append("Total Price (Tax Included): ").append(String.format("$%.2f", totalPrice)).append("\n");

        // Add selected normal toppings to order details
        for (CheckBox checkBox : normalToppingsCheckBoxes) {
            if (checkBox.isSelected()) {
                orderDetails.append(checkBox.getText()).append(", ");
            }
        }

        // Add selected meat toppings to order details
        for (CheckBox checkBox : meatToppingsCheckBoxes) {
            if (checkBox.isSelected()) {
                orderDetails.append(checkBox.getText()).append(", ");
            }
        }

        if (orderDetails.length() > "Toppings: ".length()) {
            orderDetails.setLength(orderDetails.length() - 2);
        }

        // Display an Alert dialog with the order details
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order Details");
        alert.setHeaderText(null);
        alert.setContentText(orderDetails.toString());
        alert.showAndWait();
    }

    private void showError(String message) {
        // Display error message using an Alert dialog or any other appropriate UI component
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearCheckboxes(List<CheckBox> checkboxes) {
        for (CheckBox checkBox : checkboxes) {
            if (checkBox != null) {
                checkBox.setSelected(false);
            }
        }
    }
    private double calculateBasePrice(String pizzaSize) {
        // Determine base price based on pizza size
        return switch (pizzaSize) {
            case "Small - $7.00" -> 7.00;
            case "Medium - $10.00" -> 10.00;
            case "Large - $13.00" -> 13.00;
            case "Extra Large - $15.00" -> 15.00;
            default -> 0.00; // Handle invalid input
        };
    }

    private double calculateToppingsPrice(List<CheckBox> toppingsCheckBoxes) {
        // Calculate additional charges for toppings
        double toppingsPrice = 0.0;

        // Each normal topping costs $1.10
        // Each meat topping costs $2.15
        for (CheckBox checkBox : toppingsCheckBoxes) {
            if (checkBox.isSelected()) {
                String toppingName = checkBox.getText();
                if (isMeatTopping(toppingName)) {
                    toppingsPrice += 2.15;
                } else {
                    toppingsPrice += 1.10;
                }
            }
        }
        return toppingsPrice;
    }

    private boolean isMeatTopping(String toppingName) {
        // Defining meat toppings here
        List<String> meatToppings = new ArrayList<>();
        meatToppings.add("Ground Beef");
        meatToppings.add("Shredded Chicken");
        meatToppings.add("Grilled Chicken");
        meatToppings.add("Pepperoni");
        meatToppings.add("Ham");
        meatToppings.add("Bacon");

        return meatToppings.contains(toppingName);
    }

    private double calculateCrustPrice(String crustType) {
        // Determine additional charges for crust type
        if (crustType.equals("Deep Dish (+$2.00)")) {
            return 2.00; // Additional charge for deep dish crust
        } else {
            return 0.00; // No additional charge for other crust types
        }
    }
}
