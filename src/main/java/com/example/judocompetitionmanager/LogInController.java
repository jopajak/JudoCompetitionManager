package com.example.judocompetitionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    


    public void switchToMainScene(ActionEvent e) throws IOException {
        MainScene main = new MainScene();
        Stage stage1 = new Stage();
        main.start(stage1);
        if (stage != null){
            stage.hide();
            stage.close();
        }
        /**Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         **/

    }

}
