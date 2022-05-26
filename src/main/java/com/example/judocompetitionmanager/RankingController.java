package com.example.judocompetitionmanager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RankingController implements Initializable {

    private Stage stage;
    private Parent root;
    private Scene scene;

    String[] contestants = {
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

    String currentContestant;

    @FXML
    public ListView rankingList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rankingList.getItems().addAll(contestants);
        rankingList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                currentContestant = String.valueOf(rankingList.getSelectionModel().getSelectedItem());

                System.out.println(currentContestant);

            }
        });

    }

    public void showProfile(){
        ;
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
        stage.setScene((Scene) scene);
        stage.show();
    }
}
