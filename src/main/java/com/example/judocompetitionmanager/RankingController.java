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
        Database db = Database.getInstance();
        List<Contestant> contestants = null;
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
                    //sortedContestants.add(contestants.get(j));
                    System.out.println(points[i] + " " + contestants.get(j));
                }
            }
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
