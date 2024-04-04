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
 Date: 29/03/2024
 **********************************************/
package org.example.invmanagement;

import jakarta.xml.bind.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.invmanagement.exceptions.ProductDeleteException;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import jakarta.xml.bind.Marshaller;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

import java.util.stream.Collectors;

public class MainScreenController extends HelloApplication {

    @FXML
    private Button deleteProductButton;
    @FXML
    private Button deletePartButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button addProductButton;
    @FXML
    private Button modifyProductButton;
    @FXML
    private Button addPartButton;
    @FXML
    private Button modifyPartButton;
    @FXML
    private TextField searchPartField;
    @FXML
    private TextField searchProductField;
    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private Inventory inventory = new Inventory();

    private ObservableList<Part> partsObservableList;
    private ObservableList<Product> productsObservableList;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partStockColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableColumn<Part, Integer> productIdColumn;
    @FXML
    private TableColumn<Part, String> productNameColumn;
    @FXML
    private TableColumn<Part, Integer> productStockColumn;
    @FXML
    private TableColumn<Part, Double> productPriceColumn;

    private static final Logger LOGGER = Logger.getLogger(MainScreenController.class.getName());

    @FXML
    private Button SaveToFileButton;

    @FXML
    private Button LoadFromFileButton;

    @FXML
    private Button SaveToDatabaseButton;

    @FXML
    private Button LoadFromDatabaseButton;

    @FXML
    private ChoiceBox<String> dataChoiceBox;

    @FXML
    private Button DeleteAllDataButton;

    @FXML
    private Button DeleteAllFromDBButton;

    public MainScreenController() {
        partsObservableList = FXCollections.observableArrayList(inventory.getAllParts());
        productsObservableList = FXCollections.observableArrayList(inventory.getAllProducts());
        productsObservableList.addListener((ListChangeListener<Product>) c -> {
            productsTable.setItems(productsObservableList);
        });

        /// Add sample parts to the inventory
        Part part1 = new InHouse("Engine", 500.0, 10, 1, 20, 1234);
        inventory.addPart(part1);
        Part part2 = new Outsourced("Transmission", 1200.0, 7, 1, 15, "AutoParts Co.");
        inventory.addPart(part2);
        Part part3 = new InHouse("Brake Pads", 70.0, 50, 1, 100, 5678);
        inventory.addPart(part3);
        Part part4 = new Outsourced("Windshield", 200.0, 15, 1, 30, "GlassWorks Inc.");
        inventory.addPart(part4);
        Part part5 = new InHouse("Air Filter", 10.0, 100, 1, 200, 9012);
        inventory.addPart(part5);
        Part part6 = new Outsourced("Oil Filter", 10.0, 100, 1, 200, "Filter Kings");
        inventory.addPart(part6);
        Part part7 = new InHouse("Alternator", 100.0, 10, 1, 20, 3456);
        inventory.addPart(part7);
        Part part8 = new Outsourced("Battery", 120.0, 15, 1, 30, "Energy Plus");
        inventory.addPart(part8);
        Part part9 = new InHouse("Spark Plug", 5.0, 100, 1, 200, 7890);
        inventory.addPart(part9);
        Part part10 = new Outsourced("Tire", 80.0, 50, 1, 100, "Rubber Manufacturers");
        inventory.addPart(part10);

// Add sample products to the inventory
        Product product1 = new Product("Sedan", 20000.0, 5, 1, 10);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part2);
        product1.addAssociatedPart(part3);
        inventory.addProduct(product1);

        Product product2 = new Product("SUV", 25000.0, 3, 1, 6);
        product2.addAssociatedPart(part1);
        product2.addAssociatedPart(part2);
        product2.addAssociatedPart(part4);
        inventory.addProduct(product2);

        Product product3 = new Product("Pickup Truck", 30000.0, 4, 1, 8);
        product3.addAssociatedPart(part1);
        product3.addAssociatedPart(part2);
        product3.addAssociatedPart(part5);
        inventory.addProduct(product3);

        Product product4 = new Product("Sports Car", 60000.0, 2, 1, 4);
        product4.addAssociatedPart(part1);
        product4.addAssociatedPart(part2);
        product4.addAssociatedPart(part6);
        inventory.addProduct(product4);

        Product product5 = new Product("Minivan", 28000.0, 3, 1, 6);
        product5.addAssociatedPart(part1);
        product5.addAssociatedPart(part2);
        product5.addAssociatedPart(part7);
        inventory.addProduct(product5);

        Product product6 = new Product("Convertible", 35000.0, 2, 1, 4);
        product6.addAssociatedPart(part1);
        product6.addAssociatedPart(part2);
        product6.addAssociatedPart(part8);
        inventory.addProduct(product6);

        Product product7 = new Product("Luxury Sedan", 40000.0, 3, 1, 6);
        product7.addAssociatedPart(part1);
        product7.addAssociatedPart(part2);
        product7.addAssociatedPart(part9);
        inventory.addProduct(product7);

        Product product8 = new Product("Luxury SUV", 45000.0, 2, 1, 4);
        product8.addAssociatedPart(part1);
        product8.addAssociatedPart(part2);
        product8.addAssociatedPart(part10);
        inventory.addProduct(product8);

        Product product9 = new Product("Electric Car", 35000.0, 3, 1, 6);
        product9.addAssociatedPart(part4);
        product9.addAssociatedPart(part5);
        product9.addAssociatedPart(part6);
        inventory.addProduct(product9);

        Product product10 = new Product("Hybrid", 30000.0, 4, 1, 8);
        product10.addAssociatedPart(part7);
        product10.addAssociatedPart(part8);
        product10.addAssociatedPart(part9);
        inventory.addProduct(product10);

    }

    public void updateProductsObservableList() {
        productsObservableList.setAll(inventory.getAllProducts());
    }


    public void updatePartsObservableList() {
        partsObservableList.setAll(inventory.getAllParts());
    }
    @FXML
    public void initialize() {
        updateProductsObservableList();
        updatePartsObservableList();

        // Set the cell value factories for the partsTable
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Set the items of the partsTable to the inventory's list of parts
        partsTable.setItems(partsObservableList);



        // Set the items of the productsTable to the inventory's list of products
        productsTable.setItems(productsObservableList);

        // Add a listener to the searchPartField
        searchPartField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                // If the search field is empty, display all parts
                partsTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));
            } else {
                // Otherwise, perform a search with the new value
                List<Part> matchingPartsByName = inventory.lookupPartByName(newValue);
                Part matchingPartById = inventory.lookupPartById(newValue);
                if (matchingPartById != null) {
                    matchingPartsByName.add(matchingPartById);
                }
                partsTable.setItems(FXCollections.observableArrayList(matchingPartsByName));
            }
        });

        // Add a listener to the searchProductField
        searchProductField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                // If the search field is empty, display all products
                productsTable.setItems(FXCollections.observableArrayList(inventory.getAllProducts()));
            } else {
                // Otherwise, perform a search with the new value
                List<Product> matchingProducts = inventory.lookupProduct(newValue);
                productsTable.setItems(FXCollections.observableArrayList(matchingProducts));
            }
        });
    }


    @FXML
    private void handleAddPartButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPartScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddPartsController controller = fxmlLoader.getController();
        controller.setInventory(inventory);
        controller.setMainScreenController(this);
        Stage stage = new Stage();
        controller.setDialogStage(stage);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleModifyProduct(ActionEvent event) throws IOException {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            ModifyProductController.setProductToModify(selectedProduct);
            ModifyProductController.setInventory(inventory);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyProductScreen.fxml"));
            Parent parent = fxmlLoader.load();

            ModifyProductController controller = fxmlLoader.getController();

            controller.setMainScreenController(this);

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            ModifyProductController.setDialogStage(stage);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Product Selected");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product from the table to modify");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleModifyPartButtonClick(ActionEvent event) throws IOException {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            ModifyPartController.setPartToModify(selectedPart);
            ModifyPartController.setInventory(inventory);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyPartScreen.fxml"));
            Parent parent = fxmlLoader.load();
            ModifyPartController controller = fxmlLoader.getController();
            controller.setMainScreenController(this); // Set the main screen controller
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            ModifyPartController.setDialogStage(stage);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Part Selected");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part from the table to modify");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddProductScreen.fxml"));
        fxmlLoader.setControllerFactory(c -> new AddProductController(inventory, this));
        Scene scene = new Scene(fxmlLoader.load());
        AddProductController controller = fxmlLoader.getController();
        Stage stage = new Stage();
        controller.setMainScreenController(this);
        controller.setDialogStage(stage);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    @FXML
    private void handleDeletePart(ActionEvent event) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Part");
        alert.setContentText("Are you sure you want to delete this part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            inventory.deletePart(selectedPart);
            partsObservableList.setAll(inventory.getAllParts());
            partsTable.setItems(partsObservableList);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void handleDeleteProduct() {
        // Get the selected product from the products table
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Product");
        alert.setContentText("Are you sure you want to delete this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                // Check if the product has any associated parts
                if (selectedProduct != null && !selectedProduct.getAssociatedParts().isEmpty()) {
                    throw new ProductDeleteException("Cannot delete a product that has a part assigned to it.");
                }

                // Delete the product
                inventory.deleteProduct(selectedProduct);

                // Update the products list in the main screen controller
                updateProductsObservableList();
            } catch (ProductDeleteException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("An error occurred while deleting the product.");
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void handleSearchPart(ActionEvent event) {
        String searchValue = searchPartField.getText();
        List<Part> matchingPartsByName = inventory.lookupPartByName(searchValue);
        Part matchingPartById = inventory.lookupPartById(searchValue);
        if (matchingPartById != null) {
            matchingPartsByName.add(matchingPartById);
        }
        partsTable.setItems(FXCollections.observableArrayList(matchingPartsByName));
    }

    @FXML
    private void handleSearchProduct(ActionEvent event) {
        String productName = searchProductField.getText();
        List<Product> matchingProducts = inventory.lookupProduct(productName);
        productsTable.setItems(FXCollections.observableArrayList(matchingProducts));
    }

    @FXML
    private void handleExit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Exit Program");
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            Platform.exit();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void handleSaveToFileClick(ActionEvent event) {
        String selectedData = dataChoiceBox.getValue();

        try {
            JAXBContext context;
            Marshaller marshaller;

            if (selectedData.equals("Parts")) {
                List<Part> allParts = inventory.getAllParts();
                context = JAXBContext.newInstance(PartsWrapper.class);
                marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                // Write all parts to an XML file
                marshaller.marshal(new PartsWrapper(allParts), new File("parts.xml"));
            } else if (selectedData.equals("Products")) {
                List<Product> allProducts = inventory.getAllProducts();
                context = JAXBContext.newInstance(ProductsWrapper.class);
                marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                // Write all products to an XML file
                marshaller.marshal(new ProductsWrapper(allProducts), new File("products.xml"));
            }else{
                List<Product> allProducts = inventory.getAllProducts();
                List<Part> allParts = inventory.getAllParts();
                context = JAXBContext.newInstance(AllDataWrapper.class);
                marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                // Write all products to an XML file
                marshaller.marshal(new AllDataWrapper(allParts,allProducts), new File("alldata.xml"));
            }
        } catch (JAXBException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred", e);
        }
    }

    @FXML
    private void handleLoadFromFileClick(ActionEvent event) {
        String selectedData = dataChoiceBox.getValue();

        try {
            JAXBContext context;
            Unmarshaller unmarshaller;

            if (selectedData.equals("Parts")) {
                context = JAXBContext.newInstance(PartsWrapper.class);
                unmarshaller = context.createUnmarshaller();

                // Read all parts from an XML file
                PartsWrapper partsWrapper = (PartsWrapper) unmarshaller.unmarshal(new File("parts.xml"));
                List<Part> allParts = partsWrapper.getParts();
                inventory.setAllParts(allParts);
            } else if (selectedData.equals("Products")) {
                context = JAXBContext.newInstance(ProductsWrapper.class);
                unmarshaller = context.createUnmarshaller();

                // Read all products from an XML file
                ProductsWrapper productsWrapper = (ProductsWrapper) unmarshaller.unmarshal(new File("products.xml"));
                List<Product> allProducts = productsWrapper.getProducts();
                inventory.setAllProducts(allProducts);
            }else{
                context = JAXBContext.newInstance(AllDataWrapper.class);
                unmarshaller = context.createUnmarshaller();

                // Read all products from an XML file
                AllDataWrapper allDataWrapper = (AllDataWrapper) unmarshaller.unmarshal(new File("alldata.xml"));
                List<Product> allProducts = allDataWrapper.getProducts();
                List<Part> allParts = allDataWrapper.getParts();
                inventory.setAllProducts(allProducts);
                inventory.setAllParts(allParts);
            }
            // Update the observable lists and tables
            updatePartsObservableList();
            updateProductsObservableList();
        } catch (JAXBException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred", e);
        }
    }
    @FXML
    private void handleSaveToDatabaseClick(ActionEvent event) {
        String selectedData = dataChoiceBox.getValue();

        try {
            // Establish a connection to your database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invmanagement", "root", "13uncucumA@");

            switch (selectedData) {
                case "Parts":
                    savePartsToDatabase(conn);
                    break;
                case "Products":
                    saveProductsToDatabase(conn);
                    break;
                case "All Data":
                    savePartsToDatabase(conn);
                    saveProductsToDatabase(conn);
                    break;
                default:
                    LOGGER.log(Level.WARNING, "Invalid selection");
                    break;
            }

            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred", e);
        }
    }

    private void savePartsToDatabase(Connection conn) throws SQLException {
        List<Part> allParts = inventory.getAllParts();

        // Create a PreparedStatement for inserting parts data
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO parts (id, name, stock, price, min, max, machineId, companyName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        // Insert all parts into the database
        for (Part part : allParts) {
            pstmt.setString(1, part.getId().toString());
            pstmt.setString(2, part.getName());
            pstmt.setInt(3, part.getStock());
            pstmt.setDouble(4, part.getPrice());
            pstmt.setInt(5, part.getMin());
            pstmt.setInt(6, part.getMax());

            if (part instanceof InHouse) {
                pstmt.setInt(7, ((InHouse) part).getMachineId());
                pstmt.setNull(8, java.sql.Types.VARCHAR); // set companyName to NULL
            } else if (part instanceof Outsourced) {
                pstmt.setNull(7, java.sql.Types.INTEGER); // set machineId to NULL
                pstmt.setString(8, ((Outsourced) part).getCompanyName());
            } else {
                // Handle the case where the part is not an instance of InHouse or Outsourced
                pstmt.setNull(7, java.sql.Types.INTEGER); // set machineId to NULL
                pstmt.setNull(8, java.sql.Types.VARCHAR); // set companyName to NULL
            }

            pstmt.executeUpdate();
        }
    }

    private void saveProductsToDatabase(Connection conn) throws SQLException {
        List<Product> allProducts = inventory.getAllProducts();

        // Create a PreparedStatement for inserting products data
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO products (id, name, stock, price, min, max, associatedParts) VALUES (?, ?, ?, ?, ?, ?, ?)");

        // Insert all products into the database
        for (Product product : allProducts) {
            pstmt.setString(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setInt(3, product.getStock());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getMin());
            pstmt.setInt(6, product.getMax());
            pstmt.setString(7, product.getAssociatedParts().stream()
                    .map(part -> String.valueOf(part.getId()))
                    .collect(Collectors.joining(",")));

            pstmt.executeUpdate();
        }
    }

    @FXML
    private void handleLoadFromDatabaseClick(ActionEvent event) {
        try {
            // Establish a connection to your database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invmanagement", "root", "13uncucumA@");

            // Create a Statement for executing SQL queries
            Statement stmt = conn.createStatement();

            // Get the selected data type from the choice box
            String selectedData = dataChoiceBox.getValue();

            switch (selectedData) {
                case "Parts":
                    loadPartsFromDatabase(stmt);
                    break;
                case "Products":
                    loadProductsFromDatabase(stmt);
                    break;
                case "All Data":
                    loadPartsFromDatabase(stmt);
                    loadProductsFromDatabase(stmt);
                    break;
                default:
                    LOGGER.log(Level.WARNING, "Invalid selection");
                    break;
            }

            // Close the database connection
            conn.close();

            // Update the observable lists and tables
            updatePartsObservableList();
            updateProductsObservableList();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred", e);
        }
    }

    private void loadPartsFromDatabase(Statement stmt) throws SQLException {
        // Execute a SQL SELECT query to retrieve all parts
        ResultSet rsParts = stmt.executeQuery("SELECT * FROM parts");
        while (rsParts.next()) {
            // Create a new Part object for each row in the ResultSet
            String id = rsParts.getString("id");
            String name = rsParts.getString("name");
            int stock = rsParts.getInt("stock");
            double price = rsParts.getDouble("price");
            int min = rsParts.getInt("min");
            int max = rsParts.getInt("max");
            int machineId = rsParts.getInt("machineId");
            String companyName = rsParts.getString("companyName");

            Part part;
            if (machineId > 0) {
                part = new InHouse(name, price, stock, min, max, machineId);
            } else {
                part = new Outsourced(name, price, stock, min, max, companyName);
            }
            part.setId(id);

            // Add the part to the inventory
            inventory.addPart(part);
        }
    }

    private void loadProductsFromDatabase(Statement stmt) throws SQLException {
        // Execute a SQL SELECT query to retrieve all products
        ResultSet rsProducts = stmt.executeQuery("SELECT * FROM products");
        while (rsProducts.next()) {
            // Create a new Product object for each row in the ResultSet
            String id = String.valueOf(rsProducts.getInt("id"));
            String name = rsProducts.getString("name");
            int stock = rsProducts.getInt("stock");
            double price = rsProducts.getDouble("price");
            int min = rsProducts.getInt("min");
            int max = rsProducts.getInt("max");
            String associatedParts = rsProducts.getString("associatedParts");

            Product product = new Product(name, price, stock, min, max);
            product.setId(id);

            // Add the associated parts to the product
            String[] associatedPartIds = associatedParts.split(",");
            for (String partId : associatedPartIds) {
                Part associatedPart = inventory.lookupPartById(partId);
                if (associatedPart != null) {
                    product.addAssociatedPart(associatedPart);
                }
            }

            // Add the product to the inventory
            inventory.addProduct(product);
        }
    }
    @FXML
    private void handleDeleteAllData(ActionEvent event) {
        // Clear all parts and products from the inventory
        inventory.getAllParts().clear();
        inventory.getAllProducts().clear();

        // Update the observable lists and tables
        updatePartsObservableList();
        updateProductsObservableList();
    }

    @FXML
    private void handleDeleteAllFromDB(ActionEvent event) {
        try {
            // Establish a connection to your database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invmanagement", "root", "13uncucumA@");

            // Create a PreparedStatement for deleting all parts data
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM parts");
            PreparedStatement pstmt1 = conn.prepareStatement("DELETE FROM products");

            // Execute the delete statement
            pstmt.executeUpdate();
            pstmt1.executeUpdate();

            // Close the connection
            conn.close();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred", e);
        }
    }



}
