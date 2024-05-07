package com.example.moodtrackr;

import com.example.moodtrackr.controllers.MoodInputController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MoodInputApplication extends Application {
    public static final String TITLE = "MoodInput";
    public static final int WIDTH = 600;
    public static final int HEIGHT = 100;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodInputApplication.class.getResource("mood-input.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}