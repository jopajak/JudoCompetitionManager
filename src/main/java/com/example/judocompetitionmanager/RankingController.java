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
import java.util.*;

public class RankingController implements Initializable {

    private Stage stage;
    private Parent root;
    private Scene scene;
    Contestant currentContestant;
    int[] tab;
    int N;
    int[] t;

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
    public void initialize(URL url, ResourceBundle resourceBundle){
        Database db = Database.getInstance();
        List<Contestant> contestants = null;
        ArrayList<Contestant> sortedContestants = new ArrayList<>();
        try {
            contestants = db.getCompetitorsList();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int size = contestants.size();
        int[] points = new int[size];
        for (int i = 0; i < size; i++){
            Contestant curr = contestants.get(i);
            points[i] = (int) curr.getPoints();
            System.out.println(points[i]);
        }

        Arrays.stream(points).sorted();

        for (int i = size-1; i >= 0; i-- ){
            for (int j =0; j < size; j++){
                if (contestants.get(j).getPoints() == points[i]){
                    sortedContestants.add(contestants.get(j));
                }
            }
        }

        rankingList.getItems().addAll(sortedContestants);
        currentContestant = rankingList.getItems().get(0);
        rankingList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if(currentContestant != null) {
                    System.out.println(currentContestant);
                    currentContestant = (Contestant) rankingList.getSelectionModel().getSelectedItem();

                    nameLabel.setText(currentContestant.getName() + " " + currentContestant.getSurname());
                    ageLabel.setText(String.valueOf(currentContestant.getAge()));
                    sexLabel.setText(currentContestant.getSexString());
                    weightCategoryLabel.setText(currentContestant.getWeightCategory());
                    weightLabel.setText(String.valueOf(currentContestant.getWeight() + "kg"));
                    pointsLabel.setText("Points: " + String.valueOf(currentContestant.getPoints()));
                }else if (currentContestant == null){
                    System.out.println("failed");
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