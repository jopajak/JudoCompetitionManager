package com.example.judocompetitionmanager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RankingController implements Initializable {

    private Stage stage;
    private Parent root;
    private Scene scene;
    private Contestant currentContestant;




    String[] contestants1 = {
            "Me",
            "You",
            "Us",
            "Jane",
            "Buddy",
            "Celina",
            "Jaquline",
            "Lola",
            "Ruby",
            "Rick",
            "Ed",
            "Jude",
            "Henry"};

    @FXML
    public ListView rankingList;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label sexLabel;
    @FXML
    private Label weightLabel;
    @FXML
    private Label weightCategoryLabel;
    @FXML
    private Label pointsLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Contestant> contestants = new ArrayList<>();
        contestants.add(new Contestant("Jack", "Dsfg", 25, 90.0, true));
        contestants.add(new Contestant("Mike", "Jesf", 26, 86.3, true));
        contestants.add(new Contestant("Jane", "Teaf", 24, 67.5, false));


        rankingList.getItems().addAll(contestants);
        rankingList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                currentContestant = (Contestant) rankingList.getSelectionModel().getSelectedItem();

                nameLabel.setText( currentContestant.getName() + " " + currentContestant.getSurname() );
                ageLabel.setText(String.valueOf(currentContestant.getAge()));
                sexLabel.setText(currentContestant.getSexString());
                weightCategoryLabel.setText(currentContestant.getWeightCategory());
                weightLabel.setText(String.valueOf(currentContestant.getWeight() + "kg"));
                pointsLabel.setText("Points: " + String.valueOf(currentContestant.getPoints()));

            }
        });

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
        stage.setScene((Scene) scene);
        stage.show();
    }
}
