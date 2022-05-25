package com.example.judocompetitionmanager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class rankingController implements Initializable {

    String[] contestants = { "Me", "You", "Us", "Jane",
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
}
