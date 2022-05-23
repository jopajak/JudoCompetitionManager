package com.example.judocompetitionmanager;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LogoutScene extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("logout-view.fxml"));
            stage.setTitle("JUDO COMPETITION MANAGER");
            Scene scene = new Scene(root);
            /**if (getClass().getResource("app.css") != null) {
                scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            }
             **/
            stage.getIcons().add(new Image("icon.jpg"));
            stage.setScene(scene);
            stage.setOpacity(0.75);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
