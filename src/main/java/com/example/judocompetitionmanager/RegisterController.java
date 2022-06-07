package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {
    Stage stage;
    Scene scene;
    Parent root;

    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private Label login;
    @FXML
    private Label password;


    public void registerUser(ActionEvent actionEvent) {
        Database db = Database.getInstance();
        String nameU = name.getText();
        String surnameU = surname.getText();
        String loginU = login.getText();
        String passwordU = password.getText();
        String salt = User.generateSalt();
        String hash = User.generateHash(passwordU, salt);

        User newOne = new User(loginU, nameU, surnameU, hash, salt);
        try {
            db.addUser(newOne);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        db.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        if (getClass().getResource("app.css") != null) {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("app.css")).toExternalForm());
        }
        stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene(scene);
        stage.show();

    }
}
