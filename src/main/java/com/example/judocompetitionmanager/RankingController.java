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
import org.json.JSONException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class RankingController implements Initializable {

    private Stage stage;
    private Parent root;
    private Scene scene;
    Contestant currentContestant;

    @FXML
    public ListView<Contestant> rankingList;
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
        String name, surname, age, weight, sex;
        Contestant current;
        ArrayList<Contestant> contestants = new ArrayList<>();


        //pobranie instancji kasy Database (singleton)
        Database db = Database.getInstance();
        List contestantList = null;

        try {
            contestantList = db.getCompetitorsList();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        int size = contestantList.size();

        ArrayList<String> mojaLista= new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            mojaLista= (ArrayList<String>) contestantList.get(i);
            System.out.println(mojaLista);

            name = String.valueOf(mojaLista.get(0));
            surname = String.valueOf(mojaLista.get(1));
            age = String.valueOf(mojaLista.get(2));
            weight = String.valueOf(mojaLista.get(3));
            sex = String.valueOf(mojaLista.get(4));
            Contestant newOne = new Contestant(name, surname, Integer.parseInt(age), Double.parseDouble(weight), Boolean.valueOf(sex));
            System.out.println(newOne);
            contestants.add(newOne);
        }



/*
        for (int i = 0; i < size; i++) {
            current = (Contestant) contestantList.get(i);
            System.out.println(current);

            name = String.valueOf(current.getName());
            surname = String.valueOf(current.getSurname());
            age = String.valueOf(current.getAge());
            weight = String.valueOf(current.getWeight());
            sex = String.valueOf(current.getSex());
            Contestant newOne = new Contestant(name, surname, Integer.parseInt(age), Double.parseDouble(weight), Boolean.valueOf(sex));
            System.out.println(newOne);
            contestants.add(newOne);
            //contestants.add(current);
        }

         */

        //contestants.add(new Contestant("Jan", "sfv", 34, 40.0, true));
        //contestants.add(new Contestant("Jang", "sfv", 34, 40.0, false));
        //System.out.println(contestants.get(0));


        rankingList.getItems().addAll(contestants);
        currentContestant = rankingList.getSelectionModel().getSelectedItem();
        rankingList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if(currentContestant != null) {
                    System.out.println(currentContestant + "00000");
                    currentContestant = (Contestant) rankingList.getSelectionModel().getSelectedItem();

                    nameLabel.setText(currentContestant.getName() + " " + currentContestant.getSurname());
                    ageLabel.setText(String.valueOf(currentContestant.getAge()));
                    sexLabel.setText(currentContestant.getSexString());
                    weightCategoryLabel.setText(currentContestant.getWeightCategory());
                    weightLabel.setText(String.valueOf(currentContestant.getWeight() + "kg"));
                    pointsLabel.setText("Points: " + String.valueOf(currentContestant.getPoints()));
                }else if (currentContestant == null){
                    System.out.println("p");
                }

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
