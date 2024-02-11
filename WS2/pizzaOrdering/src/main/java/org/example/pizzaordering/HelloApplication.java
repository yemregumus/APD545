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
 Date: 11/02/2024
 **********************************************/

package org.example.pizzaordering;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the UI layout
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Create a scene with the loaded FXML layout and set its dimensions
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);

        // Set the title of the stage
        stage.setTitle("Pizza Ordering");

        // Set the scene for the stage
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }
}
