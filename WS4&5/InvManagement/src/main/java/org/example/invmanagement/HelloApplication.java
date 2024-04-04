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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.invmanagement.*;
import org.example.invmanagement.Part;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Inventory Management App");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}