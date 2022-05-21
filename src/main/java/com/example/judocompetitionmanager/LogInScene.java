package com.example.judocompetitionmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInScene extends Application {

    public void start(Stage stage){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("logIn-view.fxml"));
            stage.setTitle("JUDO COMPETITION MANAGER");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.getIcons().add(new Image("icon.jpg"));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
