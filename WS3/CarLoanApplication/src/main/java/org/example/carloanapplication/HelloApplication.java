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
 Date: 04/03/2024
 **********************************************/
package org.example.carloanapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Car Loan Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}