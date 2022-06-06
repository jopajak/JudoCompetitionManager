package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

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
        System.out.println(password + userName);
        Database db = Database.getInstance();


        /** uwierzytelnienie użytkownika*/
        //boolean isAuth = db.authenticateUser(userName, password);
        db.close();

        //if(isAuth){
        if(userName.equals("Trainer") && password.equals("judo")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            root = loader.load();

            MainController mainController = loader.getController();
            mainController.displayName(userName);

            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            if (getClass().getResource("app.css") != null) {
                scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            }
            stage.getIcons().add(new Image("icon.jpg"));
            stage.setScene((Scene) scene);
            stage.setResizable(false);
            stage.show();
        }
        else{
            info.setText("Your login or password is incorrect.");
        }

    }

    public void switchToRegister(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
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
