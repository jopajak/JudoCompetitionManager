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

    public void merge(int[] tab, int left, int right, int middle){
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        int[] tabLeft = new int[leftSize];
        int[] tabRight = new int[rightSize];

        for (int i = 0; i < leftSize; i++){
            tabLeft[i] = tab[left + i];
        }
        for (int i = 0; i < rightSize; i++){
            tabRight[i] = tab[middle + 1 + i];
        }

        int indexLeft = 0;
        int indexRight = 0;
        int currentIndex;

        for(currentIndex = 1; indexLeft < leftSize && indexRight < rightSize; currentIndex++){
            if(tabLeft[indexLeft] <= tabRight[indexRight]){
                tab[currentIndex] = tabLeft[indexLeft++];

            }else{
                tab[currentIndex] = tabRight[indexRight++];

            }
            while(indexLeft < leftSize){
                tab[currentIndex++] = tabLeft[indexLeft++];
            }
            while(indexRight < rightSize){
                tab[currentIndex++] = tabRight[indexRight++];
            }

        }

    }
    private void merge_sort(int[] tab1, int l, int r){
        if (r > l){
            int m = (l + r)/2;
            merge_sort(tab1, l, m);
            merge_sort(tab1, m+1, r);
            merge(tab1, l, r, m);
        }
    }









    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        String name, surname, age, weight, sex;
        ArrayList<Contestant> contestants = new ArrayList<>();


        //pobranie instancji kasy Database (singleton)
        Database db = Database.getInstance();
        List contestantDB = null;

        try {
            contestantDB = db.getCompetitorsList();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        int size = Objects.requireNonNull(contestantDB).size();

        ArrayList<String> contestantTemp = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            contestantTemp = (ArrayList<String>) contestantDB.get(i);

            name = String.valueOf(contestantTemp.get(0));
            surname = String.valueOf(contestantTemp.get(1));
            age = String.valueOf(contestantTemp.get(2));
            weight = String.valueOf(contestantTemp.get(3));
            sex = String.valueOf(contestantTemp.get(4));
            Contestant newOne = new Contestant(
                    name,
                    surname,
                    Integer.parseInt(age),
                    Double.parseDouble(weight),
                    Boolean.valueOf(sex));
            contestants.add(newOne);
        }


        int[] points = new int[size];
        for (int i = 0; i < size; i++){
            Contestant curr = contestants.get(i);
            points[i] = (int) curr.getPoints();
            System.out.println(points[i]);
        }

        System.out.println("Points sorted");

        merge_sort(points, 0, size-1);
        for (int j=0; j < size; j++){
            System.out.println(points[j]);
        }
















        rankingList.getItems().addAll(contestants);
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
                    System.out.println("kicha");
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
