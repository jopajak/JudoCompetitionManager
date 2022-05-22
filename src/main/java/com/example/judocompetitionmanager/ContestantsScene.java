package com.example.judocompetitionmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ContestantsScene extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("contestants-view.fxml"));
            stage.setTitle("JUDO COMPETITION MANAGER");
            Scene scene = new Scene(root);
            if (getClass().getResource("app.css") != null) {
                //scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            }

            stage.getIcons().add(new Image("icon.jpg"));
            stage.setScene(scene);
            //stage.setOpacity(0.75);
            stage.show();

        } catch (Exception e){
            e.printStackTrace();
        }

        stage.setOnCloseRequest(event -> {
            //event.consume();
            logout(stage);
        });

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

}
