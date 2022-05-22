package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ContestantsController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextArea contestantsScene;

    public void print(ActionEvent e){
        //contestantsScene.setText("seferg");
        //System.out.println(contestantsScene.getText());
    }
    @FXML
    public void onAreaClick(ActionEvent e){

    }

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
        stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene((Scene) scene);
        stage.show();
    }


}
