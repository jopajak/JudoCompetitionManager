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
        String name, surname, age, weight, sex;
        ArrayList<Contestant> contestants = new ArrayList<>();
        ArrayList<Contestant> sortedContestants = new ArrayList<>();


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


        System.out.println(contestants);
        contestants.get(0).setPoints(2);
        contestants.get(1).setPoints(6);
        contestants.get(2).setPoints(4);
        contestants.get(3).setPoints(1);


        int[] points = new int[size];
        for (int i = 0; i < size; i++){
            Contestant curr = contestants.get(i);
            points[i] = (int) curr.getPoints();
            System.out.println(points[i]);
        }

        System.out.println("Points sorted");


        tab = points;
        N = size;
        t = new int[N];

        mergesort(0, N-1);
        for (int j=0; j < size; j++){
            System.out.println(points[j]);
        }

        for (int i = size-1; i >= 0; i-- ){
            for (int j =0; j < size; j++){
                if (contestants.get(j).getPoints() == points[i]){
                    sortedContestants.add(contestants.get(j));
                    System.out.println(points[i] + " " + contestants.get(j));
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
                    System.out.println("kicha");
                }

            }
        });

    }


// Sortowanie przez scalanie (mergesort)
// Tomasz Lubinski
// (c)2006 www.algorytm.org
    public void merge(int pocz, int sr, int kon)
    {
        int i,j,q;
        for (i=pocz; i<=kon; i++) t[i]=tab[i];  // Skopiowanie danych do tablicy pomocniczej
        i=pocz; j=sr+1; q=pocz;                 // Ustawienie wskaźników tablic
        while (i<=sr && j<=kon) {         // Przenoszenie danych z sortowaniem ze zbiorów pomocniczych do tablicy głównej
            if (t[i]<t[j])
                tab[q++]=t[i++];
            else
                tab[q++]=t[j++];
        }
        while (i<=sr) tab[q++]=t[i++]; // Przeniesienie nie skopiowanych danych ze zbioru pierwszego w przypadku, gdy drugi zbiór się skończył
    }

    /* Procedura sortowania tab[pocz...kon] */
    public void mergesort(int pocz, int kon)
    {
        int sr;
        if (pocz<kon) {
            sr=(pocz+kon)/2;
            mergesort(pocz, sr);    // Dzielenie lewej części
            mergesort(sr+1, kon);   // Dzielenie prawej części
            merge(pocz, sr, kon);   // Łączenie części lewej i prawej
        }
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
