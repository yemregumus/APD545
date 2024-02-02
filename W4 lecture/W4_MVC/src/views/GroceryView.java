package views;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import models.ItemList;

public class GroceryView extends Pane{

	private ItemList model;
	private ListView<String> fruitList;
	
	public ListView<String> getFruitList() {
		return fruitList;
	}

	public TextField getNewItemField() {
		return newItemField;
	}

	public Button getAddButton() {
		return addButton;
	}

	public Button getRemoveButton() {
		return removeButton;
	}

	private TextField newItemField;
	private Button addButton;
	private Button removeButton;
	
	
	
	public GroceryView(ItemList m) {
		
		model = m;
		fruitList = new ListView<String>(); 
		 newItemField = new TextField(); 
		  newItemField.relocate(10, 10); 
		  newItemField.setPrefSize(150, 25); // Create and position the "Add" Button 
		  addButton = new Button("Add"); 
		  addButton.relocate(175, 10); 
		  addButton.setPrefSize(100, 25); // Create and position the "Remove" Button 
		  removeButton = new Button("Remove"); 
		  removeButton.relocate(175, 45); 
		  removeButton.setPrefSize(100, 25);
		  /*String[] fruits = {"Apples", "Oranges", "Bananas", "Cherries", 
					"Lemons", "Pears", "Strawberries", "Peaches", 
					"Pomegranates", "Nectarines", "Apricots"}; 
		fruitList.setItems(FXCollections.observableArrayList(fruits)); */
		fruitList.relocate(10, 45); 
		fruitList.setPrefSize(150, 150); // Add all the components to the window 
		getChildren().addAll(newItemField, addButton, removeButton, fruitList); 	
		updateList();	
				
	}
	
	public void updateList() {
		String[] exactList = new String[model.getSize()];
		for(int i=0; i<model.getSize(); i++)
			exactList[i] = model.getItems()[i];
		int seleectedIndex = fruitList.getSelectionModel().getSelectedIndex();
		
		fruitList.setItems(FXCollections.observableArrayList(exactList));
		fruitList.getSelectionModel().select(seleectedIndex);
		
		addButton.setDisable(newItemField.getText().trim().length() <= 0);
		removeButton.setDisable(fruitList.getSelectionModel().getSelectedIndex()<0);
	}
	
}
