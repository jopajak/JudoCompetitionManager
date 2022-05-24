package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class NewContestantController {

    private Scene scene;
    private Parent root;
    private Stage stage;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;


    @FXML
    public void onClick(){
        ;
    }

    public void addContestant(ActionEvent e) throws IOException{
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        Double weight = Double.parseDouble(weightTextField.getText());
        Boolean sex;
        if(male.isSelected() == true){
            System.out.println(male.getText());
            sex = true;
        }else {
            System.out.println(female.getText());
            sex = false;
        }

        Contestant newOne = new Contestant(name, surname, age, weight, sex);
        System.out.println(newOne.getName() + " " + newOne.getSurname());


        FXMLLoader loader = new FXMLLoader(getClass().getResource("contestants-view.fxml"));
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
