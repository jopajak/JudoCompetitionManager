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
    private String currentName;
    @FXML
    private ListView contestantsList;


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

        Database db = Database.getInstance();
        try {
            db.addContestant(new Contestant("Jake", "sdvvf", 12, 40.0, true));
            db.addContestant(new Contestant("Jahgnfg", "sdvvf", 12, 40.0, true));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List competitors = null;
        try {
            competitors = db.getCompetitorsList();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(competitors);

        try {
            //competitors = db.getCompetitorsList();

            competitors = (ArrayList<Contestant>) db.getCompetitorsList();
            for(int i = 0; i < competitors.size(); i++){
                //contestants.add(competitors(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Contestant> list = new ArrayList<>();
        list.add(new Contestant("Jake", "sdvvf", 12, 40.0, true));
        contestantsList.getItems().addAll(list);
        contestantsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {

                currentContestant = (Contestant) contestantsList.getSelectionModel().getSelectedItem();
                currentName = currentContestant.getName();
                System.out.println(currentName);
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
        //stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene(scene);
        stage.show();
    }
}
