package com.example.judocompetitionmanager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScene extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
            /**FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
             Scene scene = new Scene(fxmlLoader.load(), 320, 240);
             **/
            stage.setTitle("JUDO COMPETITION MANAGER");
            Scene scene = new Scene(root);
            if (getClass().getResource("app.css") != null) {
                scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            }
            //stage.getIcons().add(new Image("icon.jpg"));
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(event -> {
                //event.consume();
                logout(stage);

            });

        }catch(Exception e) {
            e.printStackTrace();
        }

        //Stage firstStage = new Stage();
        //Group root = new Group();
        /**Scene scene = new Scene(root, 600, 400, Color.PURPLE);

         stage.getIcons().add(new Image("icon.jpg"));
         stage.setTitle("Judo Competition Manger");
         //stage.setWidth(420);
         //stage.setHeight(420);
         //stage.setX(100);
         //stage.setY(100);
         //stage.setFullScreen(true);
         stage.setFullScreenExitHint("to escape press Escape button ");
         stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("Esc"));

         Text text = new Text();
         text.setText("Yeah.");
         text.setX(50);
         text.setY(50);
         text.setFont(Font.font("Verdana", 50));
         text.setFill(Color.LIMEGREEN);

         Line line = new Line();
         line.setStartX(200);
         line.setStartY(200);
         line.setEndX(500);
         line.setEndY(200);
         line.setStrokeWidth(5);
         line.setStroke(Color.LIME);
         line.setOpacity(0.5);
         line.setRotate(45);

         Image image = new Image("icon.jpg");
         ImageView iconView = new ImageView(image);
         iconView.setX(400);
         iconView.setX(350);


         //root.getChildren().add(text);
         //root.getChildren().add(line);
         //root.getChildren().add(iconView);

         stage.setScene(scene);
         stage.show();
         **/
    }

    public void logout(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to log out.");
        alert.setContentText("Are you sure?");

        if(alert.showAndWait().get() == ButtonType.YES) {
            System.out.println("You're logged out.");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}