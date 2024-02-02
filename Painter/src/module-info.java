module Painter {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.fxml;
	exports controllers to javafx.graphics,javafx.fxml;
}
