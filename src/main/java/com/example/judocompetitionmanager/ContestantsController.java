package com.example.judocompetitionmanager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class ContestantsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Contestant currentContestant;
    @FXML
    private ListView<Contestant> contestantsListView;


    public void onAddClick(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newContestant-view.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

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

        ArrayList<Contestant> contestants = new ArrayList<Contestant>();
        Database db = Database.getInstance();
        List contestantDB = null;
        try {
            contestantDB = db.getCompetitorsList();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int size = contestantDB.size();
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            String name, surname, age, weight, sex;
            temp = (ArrayList<String>) contestantDB.get(i);

            name = String.valueOf(temp.get(0));
            surname = String.valueOf(temp.get(1));
            age = String.valueOf(temp.get(2));
            weight = String.valueOf(temp.get(3));
            sex = String.valueOf(temp.get(4));
            Contestant newOne = new Contestant(
                    name,
                    surname,
                    Integer.parseInt(age),
                    Double.parseDouble(weight),
                    Boolean.valueOf(sex));
            contestants.add(newOne);
        }



        contestantsListView.getItems().addAll(contestants);

//        contestantsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observableValue, Object o, Object t1) {
//
//                if(currentContestant != null) {
//                    currentContestant = (Contestant) contestantsListView.getSelectionModel().getSelectedItem();
//                    String currentName = currentContestant.getName();
//                    System.out.println(currentName);
//                }else {
//                    System.out.println("fail");
//                }
//
//            }
//        });

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
}
