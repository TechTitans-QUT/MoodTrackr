package com.example.moodtrackr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * The main application that launches the scene.
 */
public class MainApplication extends Application {
    public static final String TITLE = "MoodTrackr";
    public static final int WIDTH = 1075;
//    public static final int width = 1000; // template-page
    public static final int HEIGHT = 680;
//    public static final int height = 600; // template-page
    public static final String login = "login-view.fxml";
    public static final String template = "template-page.fxml";
    public static final String dashboard = "hello-view.fxml";
    public static final String moodInput = "mood-input-page.fxml";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(login)); // Select launching page
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}