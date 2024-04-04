package org.example.invmanagement;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.invmanagement.exceptions.*;

import java.util.Optional;

public class ModifyProductController {
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

    public static Inventory inventory;
    public static Product productToModify;
    public static Stage dialogStage;
    private MainScreenController mainScreenController;


    public ModifyProductController() {
    }

    public ModifyProductController(Inventory inventory, Product productToModify, Stage dialogStage) {
        this.inventory = inventory;
        this.productToModify = productToModify;
        this.dialogStage = dialogStage;
    }
    public ModifyProductController(Inventory _inventory, MainScreenController mainScreenController) {
        this.inventory = _inventory;
        this.mainScreenController = mainScreenController;
    }

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public static void setDialogStage(Stage _dialogStage) {
        dialogStage = _dialogStage;
    }

    public static void setInventory(Inventory _inventory) {
        inventory = _inventory;
    }

    public static void setProductToModify(Product _product) {
        productToModify = _product;
    }

    @FXML
    public void initialize() {

        // Load the product data into the text fields
        idField.setText(String.valueOf(productToModify.getId()));
        nameField.setText(productToModify.getName());
        inventoryLevelField.setText(String.valueOf(productToModify.getStock()));
        priceCostField.setText(String.valueOf(productToModify.getPrice()));
        maxField.setText(String.valueOf(productToModify.getMax()));
        minField.setText(String.valueOf(productToModify.getMin()));
        // Load the parts into the parts table
        partsTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));

        // Load the associated parts into the associated parts table
        associatedPartsTable.setItems(FXCollections.observableArrayList(productToModify.getAssociatedParts()));
        // Set up the table columns
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
    private void handleSaveButtonClick() throws ProductDetailsException, ProductPriceException, MinMaxValueException, InventoryValueException {        try {

            // Ensure that a product must have a name, price, and inventory level (default 0)
            if (nameField.getText().isEmpty() || priceCostField.getText().isEmpty() || inventoryLevelField.getText().isEmpty()) {
                throw new ProductDetailsException("Product must have a name, price, and inventory level.");
            }

            // Ensure that the price of a product cannot be less than the cost of the parts
            double price = Double.parseDouble(priceCostField.getText());
            double partsCost = associatedPartsTable.getItems().stream().mapToDouble(Part::getPrice).sum();
            if (price < partsCost) {
                throw new ProductPriceException("Product price cannot be less than the cost of the parts.");
            }

            // Preventing the minimum field from having a value above the maximum field
            // Preventing the maximum field from having a value below the minimum field
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            if (min > max) {
                throw new MinMaxValueException("Min value cannot be greater than max value.");
            }

            // Entering an inventory value greater than the maximum value for a part or product, or lower than the minimum value for a part or product
            int inventory = Integer.parseInt(inventoryLevelField.getText());
            if (inventory < min || inventory > max) {
                throw new InventoryValueException("Inventory value must be between min and max values.");
            }

            // Ensuring that a product must always have at least one part
            if (associatedPartsTable.getItems().isEmpty()) {
                throw new ProductPartsException("Product must have at least one part.");
            }


        // Save modifications to the product
            productToModify.setName(nameField.getText());
            productToModify.setPrice(price);
            productToModify.setStock(inventory);
            productToModify.setMax(max);
            productToModify.setMin(min);
            productToModify.setAssociatedParts(associatedPartsTable.getItems());

            mainScreenController.updateProductsObservableList();

            // Close the dialog
            dialogStage.close();
        } catch (ProductDetailsException | ProductPriceException | MinMaxValueException | InventoryValueException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred while saving the product.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (ProductPartsException e) {
        throw new RuntimeException(e);
    }
    }

    @FXML
    private void handleAddButtonClick() {
        // Get the selected part from the parts table
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