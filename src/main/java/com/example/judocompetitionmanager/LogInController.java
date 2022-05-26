package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class LogInController {

    @FXML
    TextField nameTextField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label info;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMainScene(ActionEvent e) throws IOException {

        String password = passwordField.getText();
        String userName = nameTextField.getText();
        if(password.equals("judo") && userName.equals("judo")){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            root = loader.load();

            MainController mainController = loader.getController();
            mainController.displayName(userName);

            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            if (getClass().getResource("app.css") != null) {
                scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            }
            //stage.getIcons().add(new Image("icon.jpg"));
            stage.setScene((Scene) scene);
            stage.show();
        }
        else{
            info.setText("Your login or password is incorrect.");
        }


        }

}
