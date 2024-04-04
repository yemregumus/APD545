package org.example.invmanagement;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.invmanagement.exceptions.*;

import java.util.Optional;

public class AddPartsController {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField inventoryLevelField;
    @FXML
    private TextField priceCostField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField minField;
    @FXML
    private RadioButton inHouseRadioButton;
    @FXML
    private RadioButton outsourcedRadioButton;
    @FXML
    private TextField companyMachineIdField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    @FXML
    public ToggleGroup partTypeGroup;
    private Inventory inventory;

    private Stage dialogStage;

    private MainScreenController mainScreenController;

    public AddPartsController(Inventory inventory, MainScreenController mainScreenController) {
        this.inventory = inventory;
        this.mainScreenController = mainScreenController;
    }


    public AddPartsController() {

    }
    @FXML
    public void initialize() {
        // Initialization logic goes here
        partTypeGroup = new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(partTypeGroup);
        this.outsourcedRadioButton.setToggleGroup(partTypeGroup);
    }
    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @FXML
    private void handleSaveButtonClick() {
        try {
            String name = nameField.getText();
            int inventoryLevel = Integer.parseInt(inventoryLevelField.getText());
            double priceCost = Double.parseDouble(priceCostField.getText());
            int max = Integer.parseInt(maxField.getText());
            int min = Integer.parseInt(minField.getText());
            String companyMachineId = companyMachineIdField.getText();

            // Ensure that a part must have a name, price, and inventory level (default 0)
            if (name.isEmpty() || priceCostField.getText().isEmpty() || inventoryLevelField.getText().isEmpty()) {
                throw new PartDetailsException("Part must have a name, price, and inventory level.");
            }

            // Preventing the minimum field from having a value above the maximum field
            // Preventing the maximum field from having a value below the minimum field
            if (min > max) {
                throw new MinMaxValueException("Min value cannot be greater than max value.");
            }

            // Entering an inventory value greater than the maximum value for a part or product, or lower than the minimum value for a part or product
            if (inventoryLevel < min || inventoryLevel > max) {
                throw new InventoryValueException("Inventory value must be between min and max values.");
            }

            Part part;
            if (inHouseRadioButton.isSelected()) {
                part = new InHouse(name, priceCost, inventoryLevel, min, max, Integer.parseInt(companyMachineId));
            } else {
                part = new Outsourced(name, priceCost, inventoryLevel, min, max, companyMachineId);
            }
            inventory.addPart(part);

            nameField.clear();
            inventoryLevelField.clear();
            priceCostField.clear();
            maxField.clear();
            minField.clear();
            companyMachineIdField.clear();

            mainScreenController.updatePartsObservableList();

            dialogStage.close();
        } catch (PartDetailsException | MinMaxValueException | InventoryValueException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred while saving the part.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancelButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Part Creation");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){


            // Close the dialog
            dialogStage.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
}