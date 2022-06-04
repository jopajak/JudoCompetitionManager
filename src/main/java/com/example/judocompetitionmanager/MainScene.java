package com.example.judocompetitionmanager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScene extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
            /**FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
             Scene scene = new Scene(fxmlLoader.load(), 320, 240);
             **/
            stage.setTitle("JUDO COMPETITION MANAGER");
            Scene scene = new Scene(root);
            if (getClass().getResource("app.css") != null) {
                scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            }
            //stage.getIcons().add(new Image("icon.jpg"));
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(event -> {
                //event.consume();
                logout(stage);

            });

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void logout(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to log out.");
        alert.setContentText("Are you sure?");

        if(alert.showAndWait().get() == ButtonType.YES) {
            System.out.println("You're logged out.");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}