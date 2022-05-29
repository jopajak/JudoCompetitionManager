package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewContestantController implements Initializable {

    private Scene scene;
    private Parent root;
    private Stage stage;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private Spinner<Integer> ageSpinner;
    @FXML
    private Spinner<Double> weightSpinner;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;

    public void addContestant(ActionEvent e) throws IOException, JSONException {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        boolean sex;

        if (!name.equals("") &&
            !surname.equals("") &&
            ageSpinner.getValue() != null &&
            weightSpinner.getValue() != null){

            int age = ageSpinner.getValue();
            Double weight = weightSpinner.getValue();
            if(male.isSelected()){
                sex = true;
            }else {
                sex = false;
            }

            Contestant newOne = new Contestant(name, surname, age, weight, sex);
            System.out.println("New contestant added successfully!");

            Database db = Database.getInstance();
            db.addContestant(newOne);


            FXMLLoader loader = new FXMLLoader(getClass().getResource("contestants-view.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            if (getClass().getResource("app.css") != null) {
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("app.css")).toExternalForm());
            }
            stage.setScene(scene);
            stage.show();
        }

    }


    @FXML
    public void switchToMainScene(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        if (getClass().getResource("app.css") != null) {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("app.css")).toExternalForm());
        }
        //stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactoryAge =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactoryAge.setValue(20);
        ageSpinner.setValueFactory(valueFactoryAge);

        SpinnerValueFactory<Double> valueFactoryWeight =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 200);
        valueFactoryWeight.setValue(50.0);
        weightSpinner.setValueFactory(valueFactoryWeight);

        }

}

