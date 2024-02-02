package application;

import javafx.application.Application; //ctrl+shift+o
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyApplication extends Application{

	@Override
	public void start(Stage ps) throws Exception {
		
		//if you want to exit your application by choice
		//Platform.exit();
		Pane p = new Pane();
		Button ok_btn = new Button("Ok Button");
		ok_btn.setAlignment(Pos.CENTER);
		p.getChildren().add(ok_btn);
		
		/*ok_btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Pressed Button ok");
				
			}
			
		});*/
		
		ok_btn.setOnAction(e->System.out.println("button ok is pressed"));
		
		Scene sc = new Scene(p,300,100);
		ps.setTitle("My First Window");
		ps.setScene(sc);
		ps.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
