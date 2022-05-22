package com.example.judocompetitionmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class MainController {

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("myContestants-view.fxml"));


    }
    @FXML
    public void onMyContestantsClick(ActionEvent e) {

    }
}