package org.example.invmanagement;  import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.invmanagement.exceptions.*;
import javafx.stage.Stage;

import java.util.Optional;

public class ModifyPartController {
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

    public static Inventory inventory;
    public static Part partToModify;
    public static Stage dialogStage;

    private MainScreenController mainScreenController;

    public ModifyPartController() {
    }

    public ModifyPartController(Inventory inventory, Part partToModify, Stage dialogStage) {
        this.inventory = inventory;
        this.partToModify = partToModify;
        this.dialogStage = dialogStage;
    }
    public ModifyPartController(Inventory inventory, MainScreenController mainScreenController) {
        this.inventory = inventory;
        this.mainScreenController = mainScreenController;
    }

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }
    public static void setDialogStage(Stage _dialogStage) {
        dialogStage = _dialogStage;
    }
    @FXML
    public static void setInventory(Inventory _inventory) {
        inventory = _inventory;
    }


    public static void setPartToModify(Part _partToModify) {
        partToModify = _partToModify;
    }
    @FXML
    public void initialize() {

        inHouseRadioButton.setDisable(true);
        outsourcedRadioButton.setDisable(true);
        // Populate the fields with the existing data of the part to be modified
        idField.setText(String.valueOf(partToModify.getId()));
        nameField.setText(partToModify.getName());
        inventoryLevelField.setText(String.valueOf(partToModify.getStock()));
        priceCostField.setText(String.valueOf(partToModify.getPrice()));
        maxField.setText(String.valueOf(partToModify.getMax()));
        minField.setText(String.valueOf(partToModify.getMin()));

        if (partToModify instanceof InHouse) {
            inHouseRadioButton.setSelected(true);
            companyMachineIdField.setText(String.valueOf(((InHouse) partToModify).getMachineId()));
        } else {
            outsourcedRadioButton.setSelected(true);
            companyMachineIdField.setText(((Outsourced) partToModify).getCompanyName());
        }
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

            // Update the part with the new data from the fields
            partToModify.setName(name);
            partToModify.setPrice(priceCost);
            partToModify.setStock(inventoryLevel);
            partToModify.setMax(max);
            partToModify.setMin(min);
            if (partToModify instanceof InHouse) {
                ((InHouse) partToModify).setMachineId(Integer.parseInt(companyMachineId));
            } else {
                ((Outsourced) partToModify).setCompanyName(companyMachineId);
            }

            mainScreenController.updatePartsObservableList();

            // Close the dialog
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
        alert.setHeaderText("Cancel Part Modification");
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