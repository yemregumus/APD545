package org.example.invmanagement;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.invmanagement.exceptions.*;

import java.util.Optional;

public class AddProductController {
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
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeAssociatedPartButton;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partStockColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartIdColumn;
    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;
    @FXML
    private TableColumn<Part, Integer> associatedPartStockColumn;
    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableView<Part> associatedPartsTable;

    private Inventory inventory;
    private Stage dialogStage;
    private MainScreenController mainScreenController;

    public AddProductController(Inventory inventory, MainScreenController mainScreenController) {
        this.inventory = inventory;
        this.mainScreenController = mainScreenController;
    }

    public AddProductController() {
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
    public void initialize() {

        // Initialization logic goes here
        partsTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));


        // Set the cell value factories for the partsTable
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
    @FXML
    private void handleSaveButtonClick() {
        try {
            String name = nameField.getText();
            int inventoryLevel = Integer.parseInt(inventoryLevelField.getText());
            double price = Double.parseDouble(priceCostField.getText());
            int max = Integer.parseInt(maxField.getText());
            int min = Integer.parseInt(minField.getText());

            // Ensure that a product must have a name, price, and inventory level (default 0)
            if (name.isEmpty() || priceCostField.getText().isEmpty() || inventoryLevelField.getText().isEmpty()) {
                throw new ProductDetailsException("Product must have a name, price, and inventory level.");
            }

            // Ensure that the price of a product cannot be less than the cost of the parts
            double partsCost = associatedPartsTable.getItems().stream().mapToDouble(Part::getPrice).sum();
            if (price < partsCost) {
                throw new ProductPriceException("Product price cannot be less than the cost of the parts.");
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


            // Ensuring that a product must always have at least one part
            if (associatedPartsTable.getItems().isEmpty()) {
                throw new ProductPartsException("Product must have at least one part.");
            }

            Product newProduct = new Product(name, price, inventoryLevel, min, max);
            for (Part part : associatedPartsTable.getItems()) {
                newProduct.addAssociatedPart(part);
            }

            inventory.addProduct(newProduct);
            mainScreenController.updatePartsObservableList();
            mainScreenController.updateProductsObservableList();
            dialogStage.close();
        } catch (ProductDetailsException | ProductPriceException | MinMaxValueException | InventoryValueException |
                 ProductPartsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred while saving the product.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddButtonClick() {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedPartsTable.getItems().add(selectedPart);
        }
    }

    @FXML
    private void handleRemoveAssociatedPartButtonClick() {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedPartsTable.getItems().remove(selectedPart);
        }
    }

    @FXML
    private void handleCancelButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Product Creation");
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