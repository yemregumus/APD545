package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Picker extends Application{

	@Override
	public void start(Stage ps) throws Exception {
		ps.setTitle("Hero Picker Application");
		
		BorderPane root = new BorderPane();
		Scene sc = new Scene(root, 500, 350, Color.WHITE);
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPrefHeight(Double.MAX_VALUE);
		ColumnConstraints cl1 = new ColumnConstraints(150,150,Double.MAX_VALUE);
		ColumnConstraints cl2 = new ColumnConstraints(50);
		ColumnConstraints cl3 = new ColumnConstraints(150,150,Double.MAX_VALUE);
		
		cl1.setHgrow(Priority.ALWAYS);
		cl3.setHgrow(Priority.ALWAYS);
		
		gp.getColumnConstraints().addAll(cl1,cl2,cl3);
		
		Label clbl = new Label("Candidates");
		gp.add(clbl, 0, 0);
		gp.setHalignment(clbl, HPos.CENTER);
		
		Label hlbl = new Label("Heros");
		gp.add(hlbl, 2, 0);
		gp.setHalignment(hlbl, HPos.CENTER);
		
		ObservableList<String> candidates = FXCollections.observableArrayList("Superman",
				"Spiderman", "Wolverine", "Police", "Fire Rescue", "Soldiers",
				"Mom & Dad", "Doctors","Politicians");
		
		ListView<String> clv = new ListView<>(candidates);
		gp.add(clv, 0, 1);
		
		ObservableList<String> heros = FXCollections.observableArrayList();
		ListView<String> hLV = new ListView<>(heros);
		gp.add(hLV, 2, 1);
		
		Button sendRB = new Button (" > ");
		sendRB.setOnAction(e->{
			String potential = clv.getSelectionModel().getSelectedItem();
			if(potential != null) {
				clv.getSelectionModel().clearSelection();
				candidates.remove(potential);
				heros.add(potential);
			}
		});
		
		Button sendLB = new Button (" < ");
		sendLB.setOnAction(e->{
			String hero = hLV.getSelectionModel().getSelectedItem();
			if(hero != null) {
				hLV.getSelectionModel().clearSelection();
				heros.remove(hero);
				candidates.add(hero);
			}
		});
		
		VBox vb = new VBox(5);
		vb.getChildren().addAll(sendRB, sendLB);
		vb.setAlignment(Pos.CENTER);
		gp.add(vb, 1, 1);
		root.setCenter(gp);
		
		gp.setVgrow(root, Priority.ALWAYS);
		ps.setScene(sc);
		ps.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
