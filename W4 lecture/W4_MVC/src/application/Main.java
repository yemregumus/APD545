package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.ItemList;
import views.GroceryView;

public class Main extends Application{

	private ItemList model;
	private GroceryView gv;
	
	public Main() {
		model = new ItemList();
		gv = new GroceryView(model);
	}
	

	@Override
	public void start(Stage ps) throws Exception {
	
		gv.getNewItemField().setOnKeyReleased(e->{
			gv.getAddButton().setDisable(gv.getNewItemField().getText().trim().length() <= 0);
		});
		
		gv.getFruitList().setOnMousePressed(e->{
			handleListSelection();
		});
		
		gv.getAddButton().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String temp = gv.getNewItemField().getText().trim();
				if(temp.length() > 0 ) {
					model.add(temp);
					gv.updateList();
				}
				gv.getNewItemField().clear();
			}
		});
		
		gv.getRemoveButton().setOnAction(e->{
			int selectedIndex = gv.getFruitList().getSelectionModel().getSelectedIndex();
			if(selectedIndex >= 0) {
				model.remove(selectedIndex);
				gv.updateList();
			}
		});
		
		ps.setResizable(false);
		ps.setTitle("Grocery List");
		ps.setScene(new Scene(gv, 300,300));
		ps.show();
		
	}
	
	private void handleListSelection() {
		gv.updateList();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
