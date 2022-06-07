package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContestsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Contestant contestant;
    private int points;
    @FXML
    private ComboBox judokaComboBox;
    @FXML
    private TextField contest;
    @FXML
    private Spinner<Integer> pointsSpinner;

    public ContestsController() {
    }


    public void addPoints(ActionEvent e) {
        points = pointsSpinner.getValue();

        Database db =  Database.getInstance();
        String key = contestant.getName() + " " + contestant.getSurname();
        try {
            db.getContestant(key);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        contestant.addPoints(points);
        try {
            db.addContestant(contestant);
            System.out.println("Points have been added succesfully!");
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        if (getClass().getResource("app.css") != null) {
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        }
        stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene(scene);
        stage.show();


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
        stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Database db = Database.getInstance();
        List<Contestant> contestants = null;
        try {
            contestants = db.getCompetitorsList();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        db.close();

        judokaComboBox.getItems().addAll(contestants);
        judokaComboBox.setOnAction(this::getJudoka);

        SpinnerValueFactory<Integer> valueFactoryPoints =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3);
        valueFactoryPoints.setValue(0);
        pointsSpinner.setValueFactory(valueFactoryPoints);

    }

    private void getJudoka(Event event) {
        Contestant current = (Contestant) judokaComboBox.getValue();
        contestant = current;
    }


}
