package org.example.carloanapplication;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloController {
    @FXML
    private TextField customerNameTextField;

    @FXML
    private TextField customerNumberTextField;

    @FXML
    private ChoiceBox<String> vehicleTypeChoice;

    @FXML
    private ChoiceBox<String> vehicleAgeChoice;

    @FXML
    private TextField vehiclePriceTextField;

    @FXML
    private TextField downPaymentTextField;

    @FXML
    private TextField interestRateTextField;

    @FXML
    private Slider loanPeriodSlider;

    @FXML
    private Label loanPeriodLabel;

    @FXML
    private RadioButton weeklyRadioButton;

    @FXML
    private RadioButton biWeeklyRadioButton;

    @FXML
    private RadioButton monthlyRadioButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button calculateButton;

    @FXML
    private Button saveRatesButton;

    @FXML
    private Button showSavedRatesButton;

    @FXML
    private Label paymentResultLabel;

    @FXML
    // Toggle group for payment frequency radio buttons
    private ToggleGroup paymentFrequencyToggleGroup = new ToggleGroup();

    @FXML
    // Used Map to store saved rates data
    private Map<String, Object> savedRatesData = new HashMap<>();

    @FXML
    public void initialize() {
        // Setting toggle group for payment frequency radio buttons
        weeklyRadioButton.setToggleGroup(paymentFrequencyToggleGroup);
        biWeeklyRadioButton.setToggleGroup(paymentFrequencyToggleGroup);
        monthlyRadioButton.setToggleGroup(paymentFrequencyToggleGroup);

        // Adding listeners for input fields and slider
        addListeners();

        // Initially disable buttons to avoid weird errors

        calculateButton.setDisable(true);
        saveRatesButton.setDisable(true);
        showSavedRatesButton.setDisable(true);
    }

    @FXML
    private void addListeners() {
        // Add change listener to loanPeriodSlider to update loanPeriodLabel
        loanPeriodSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int months = newValue.intValue();
            loanPeriodLabel.setText(months + " months");
            updateButtonsState();
        });

        // Add listener to radio buttons to update button states
        weeklyRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> updateButtonsState());
        biWeeklyRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> updateButtonsState());
        monthlyRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> updateButtonsState());

        // Add listeners for text fields
        addInputListeners(customerNameTextField);
        addNumericInputListeners(customerNumberTextField);
        addNumericInputListeners(vehiclePriceTextField);
        addNumericInputListeners(downPaymentTextField);
        addNumericInputListeners(interestRateTextField);
    }

    @FXML
    // Adding listener to input fields to allow only alphabetic characters
    private void addInputListeners(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\sa-zA-Z]*")) {
                textField.setText(oldValue);
            }
            updateButtonsState();
        });
    }

    @FXML
    // Adding listener to input fields to allow only numeric characters
    private void addNumericInputListeners(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            updateButtonsState();
        });
    }

    // Update button states based on input field values
    @FXML
    private void updateButtonsState() {
        boolean hasValidInput = validateInputFields();
        calculateButton.setDisable(!hasValidInput);
        saveRatesButton.setDisable(!hasValidInput);
        showSavedRatesButton.setDisable(!hasValidInput);
    }

    // Validate input fields
    @FXML
    private boolean validateInputFields() {
        return !isEmpty(customerNameTextField.getText()) &&
                !isEmpty(customerNumberTextField.getText()) &&
                vehicleTypeChoice.getValue() != null &&
                vehicleAgeChoice.getValue() != null &&
                !isEmpty(vehiclePriceTextField.getText()) &&
                !isEmpty(downPaymentTextField.getText()) &&
                !isEmpty(interestRateTextField.getText());
    }

    // Check if a string is empty
    @FXML
    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    @FXML
    private void handleClearButton() {
        // Clear all input fields and labels
        customerNameTextField.clear();
        customerNumberTextField.clear();
        vehicleTypeChoice.setValue(null);
        vehicleAgeChoice.setValue(null);
        vehiclePriceTextField.clear();
        downPaymentTextField.clear();
        interestRateTextField.clear();
        loanPeriodSlider.setValue(12);
        weeklyRadioButton.setSelected(true);
        paymentResultLabel.setText("");
    }

    @FXML
    private void handleCalculateButton() {
        // Calculate loan and display result
        Map<String, Object> loanData = calculateLoan();
        // Get the selected payment frequency
        String paymentFrequency = getPaymentFrequency();
        // Display loan data including payment frequency
        displayLoanData(loanData, paymentFrequency);

    }

    @FXML
    // Calculate loan details
    private Map<String, Object> calculateLoan() {
        Map<String, Object> loanData = new HashMap<>();

        // Get input values
        double vehiclePrice = Double.parseDouble(vehiclePriceTextField.getText());
        double downPayment = Double.parseDouble(downPaymentTextField.getText());
        double interestRate = Double.parseDouble(interestRateTextField.getText()) / 100.0; // convert percentage to decimal
        int loanPeriodMonths = (int) loanPeriodSlider.getValue();

        // Calculate loan amount
        double loanAmount = vehiclePrice - downPayment;

        // Calculate monthly interest rate
        double monthlyInterestRate = interestRate / 12.0;

        // Calculate payment frequency
        int paymentFrequency = 4; // 4 for weekly, 2 for bi-weekly, 1 for monthly
        if (biWeeklyRadioButton.isSelected()) {
            paymentFrequency = 2;
        } else if (monthlyRadioButton.isSelected()) {
            paymentFrequency = 1;
        }

        // Calculate total payment and interest based on frequency
        double totalInterest = 0.0;
        double totalPayment = 0.0;
        double paymentAmount = 0.0;
        if (interestRate != 0) {
            double temp = Math.pow(1 + monthlyInterestRate / paymentFrequency, loanPeriodMonths * paymentFrequency);
            totalPayment = (loanAmount * monthlyInterestRate / paymentFrequency * temp) / (temp - 1);
            totalInterest = totalPayment * loanPeriodMonths * paymentFrequency - loanAmount;
            paymentAmount = totalPayment;
        } else {
            totalPayment = loanAmount / (loanPeriodMonths * paymentFrequency);
            paymentAmount = totalPayment;
        }

        // Add loan data to map
        loanData.put("Total Payment", totalPayment);
        loanData.put("Total Interest", totalInterest);
        loanData.put("Payment Amount", paymentAmount);

        return loanData;
    }

    @FXML
    private void handleSaveRatesButton() {
        // Calculate loan and save data
        Map<String, Object> loanData = calculateLoan();

        // Add additional data to be saved
        loanData.put("Customer Name", customerNameTextField.getText());
        loanData.put("Customer Number", customerNumberTextField.getText());
        loanData.put("Vehicle Type", vehicleTypeChoice.getValue());
        loanData.put("Vehicle Age", vehicleAgeChoice.getValue());
        loanData.put("Payment Frequency", getPaymentFrequency());

        // Save data
        savedRatesData = loanData;
    }

    @FXML
    private void handleShowSavedRatesButton() {
        // Display saved rates data
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Saved Rates");
        alert.setHeaderText("Saved Rates");

        // Prepare the content text with data in the desired order
        StringBuilder contentText = new StringBuilder();
        appendIfPresent(contentText, "Customer Name", savedRatesData);
        appendIfPresent(contentText, "Customer Number", savedRatesData);
        appendIfPresent(contentText, "Vehicle Age", savedRatesData);
        appendIfPresent(contentText, "Vehicle Type", savedRatesData);
        appendIfPresent(contentText, "Payment Amount", savedRatesData);
        appendIfPresent(contentText, "Payment Frequency", savedRatesData);
        appendIfPresent(contentText, "Total Interest", savedRatesData);

        alert.setContentText(contentText.toString());

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        alert.showAndWait();
    }


    // Utility method to append data if present with formatting
    @FXML
    private void appendIfPresent(StringBuilder contentText, String key, Map<String, Object> data) {
        if (data.containsKey(key)) {
            Object value = data.get(key);
            if (value instanceof Double) {
                double doubleValue = (double) value;
                contentText.append(key).append(": ").append(String.format("$%.2f", doubleValue)).append("\n");
            } else {
                contentText.append(key).append(": ").append(value).append("\n");
            }
        }
    }

    @FXML
    private void handlePaymentFrequencyChange() {
        // Disable other radio buttons when one is selected
        RadioButton selectedRadioButton = (RadioButton) paymentFrequencyToggleGroup.getSelectedToggle();
        if (selectedRadioButton == weeklyRadioButton) {
            biWeeklyRadioButton.setDisable(true);
            monthlyRadioButton.setDisable(true);
        } else if (selectedRadioButton == biWeeklyRadioButton) {
            weeklyRadioButton.setDisable(true);
            monthlyRadioButton.setDisable(true);
        } else if (selectedRadioButton == monthlyRadioButton) {
            weeklyRadioButton.setDisable(true);
            biWeeklyRadioButton.setDisable(true);
        }
    }
    @FXML
    private void displayLoanData(Map<String, Object> loanData, String paymentFrequency) {
        double totalPayment = (double) loanData.get("Total Payment");
        double totalInterest = (double) loanData.get("Total Interest");
        double paymentAmount = (double) loanData.get("Payment Amount");

        // Construct result text including payment frequency
        String resultText = String.format("%s Payment: $%.2f%nTotal Interest: $%.2f",
                paymentFrequency, totalPayment, totalInterest, paymentAmount);

        paymentResultLabel.setText(resultText);
    }
    @FXML
    private String getPaymentFrequency() {
        if (weeklyRadioButton.isSelected()) {
            return "Weekly";
        } else if (biWeeklyRadioButton.isSelected()) {
            return "Bi-Weekly";
        } else {
            return "Monthly";
        }
    }
}
