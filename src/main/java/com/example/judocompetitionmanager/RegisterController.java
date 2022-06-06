package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.JSONException;

public class RegisterController {
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

    }
}
