package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContestsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Contestant contestant;
    private int points;
    @FXML
    private Spinner<Contestant> judokaSpinner;
    @FXML
    private TextField contest;
    @FXML
    private Spinner<Integer> pointsSpinner;

    public ContestsController() {
    }


    public void addPoints(ActionEvent e) {
        contestant = judokaSpinner.getValue();
        points = pointsSpinner.getValue();

        contestant.setPoints(points);

    }

    public void goBack(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        if (getClass().getResource("app.css") != null) {
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        }
        //stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //SpinnerValueFactory<Contestant> valueFactoryContestant =
         //       new SpinnerValueFactory.ListSpinnerValueFactory<Contestant>();

        SpinnerValueFactory<Integer> valueFactoryPoints =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3);
        valueFactoryPoints.setValue(0);
        pointsSpinner.setValueFactory(valueFactoryPoints);


    }
}
