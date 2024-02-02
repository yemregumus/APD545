package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class HomeController {

	private enum PenSize{
		SMALL(2),
		MEDIUM(4),
		LARGE(6);
		
		private final int radius;
		PenSize(int rad){this.radius = rad;}
		public int getRadius() {
			return radius;
		}
	};
	
	
    @FXML
    private ToggleGroup ColorGroup;

    @FXML
    private RadioButton blackRB;

    @FXML
    private RadioButton blueRB;

    @FXML
    private Button clearBtn;

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton greenRB;

    @FXML
    private RadioButton largeSG;

    @FXML
    private RadioButton mediumSG;

    @FXML
    private RadioButton redRB;

    @FXML
    private ToggleGroup sizeGroup;

    @FXML
    private RadioButton smallSG;

    @FXML
    private Button undoBtn;

    private PenSize radius = PenSize.SMALL;
    private Paint color = Color.BLUE;
    
    public void initialize() {
    	blackRB.setUserData(Color.BLACK);
    	blueRB.setUserData(Color.BLUE);
    	greenRB.setUserData(Color.GREEN);
    	redRB.setUserData(Color.RED);
    	
    	smallSG.setUserData(PenSize.SMALL);
    	mediumSG.setUserData(PenSize.MEDIUM);
    	largeSG.setUserData(PenSize.LARGE);
    }
    
    @FXML
    void drawingAreaPaneMouseDragged(MouseEvent event) {
    	Circle newCircle = new Circle(event.getX(),event.getY(),
    								radius.getRadius(), color);
    	drawingAreaPane.getChildren().add(newCircle);
    }
    
    @FXML
    void colorRadioButtonSelected(ActionEvent event) {
    	color = (Color) ColorGroup.getSelectedToggle().getUserData();
    			
    }
    
    @FXML
    void sizeRadioButtonSelected(ActionEvent event) {
    	radius = (PenSize)sizeGroup.getSelectedToggle().getUserData();
    }
    
    @FXML
    void clearButtonPressed(ActionEvent event) {
    		drawingAreaPane.getChildren().clear();
    }

    @FXML
    void undoBtnPressed(ActionEvent event) {
    	int counter = drawingAreaPane.getChildren().size();
    	
    	if(counter > 0) {
    		drawingAreaPane.getChildren().remove(counter-1);
    	}
    }
    
}
