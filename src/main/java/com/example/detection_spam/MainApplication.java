package com.example.detection_spam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {



    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("connection-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        stage.setTitle("Tri des mails");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);


    }

    public static void main(String[] args) {
        launch();
    }
}