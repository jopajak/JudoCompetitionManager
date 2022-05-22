package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class NewContestantController {

    private Scene scene;
    private Parent root;
    private Stage stage;

    @FXML
    public void onClick(){
        ;
    }

    public void addContestant(ActionEvent e){

    }

    @FXML
    public void switchToMainScene(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        if (getClass().getResource("app.css") != null) {
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        }
        stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene((Scene) scene);
        stage.show();
    }
}
