package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class logoutController {

    @FXML
    private Button logoutButton;
    @FXML
    AnchorPane logoutScene;

    Stage stage;

    public void logout(ActionEvent e) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to log out.");
        alert.setContentText("Are you sure?");

        if(alert.showAndWait().get() == ButtonType.YES) {
            stage = (Stage) logoutScene.getScene().getWindow();
            System.out.println("You're logged out.");
            stage.close();
        }
    }


}
