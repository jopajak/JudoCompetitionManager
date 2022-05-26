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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContestantsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    ArrayList<Contestant> contestants = new ArrayList<Contestant>();
    ArrayList<Contestant> winners = new ArrayList<Contestant>();

    // inicjalizacja listy testowej

    private Contestant currentContestant;

    @FXML
    private ListView contestantsList;

    public void onAddClick(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newContestant-view.fxml"));
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
        //stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene((Scene) scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Contestant> contestants = new ArrayList<Contestant>();
        contestants.add(new Contestant("Jack", "D", 25, 90.0, false));

        contestantsList.getItems().addAll(contestants);
        contestantsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {

                currentContestant = (Contestant) contestantsList.getSelectionModel().getSelectedItem();
                System.out.println(currentContestant.getName());
            }
        });


    }
}
