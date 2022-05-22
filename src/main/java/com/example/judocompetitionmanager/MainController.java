package com.example.judocompetitionmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label nameLabel;
    public void displayName(String userName) {
        if(nameLabel != null){
            nameLabel.setText("Welcome Coach " + userName + "!");
        }
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void onButtonClick(ActionEvent e) {
        System.out.println("Print");
    }

    @FXML
    public void onRankingClick(ActionEvent e) {
        ;
    }

    @FXML
    public void onContestsClick(ActionEvent e) {

    }
    @FXML
    public void onMyContestantsClick(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("contestants-view.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ContestantsController contestantController = loader.getController();

        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        if (getClass().getResource("app.css") != null) {
            scene.getStylesheets().add(getClass().getResource("def.css").toExternalForm());
        }
        stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene((Scene) scene);
        stage.show();
    }
}