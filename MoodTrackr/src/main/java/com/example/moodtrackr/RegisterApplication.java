package com.example.moodtrackr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterApplication extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "Register Page";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterApplication.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
//        String stylesheet = Objects.requireNonNull(RegisterApplication.class.getResource("stylesheet.css")).toExternalForm();
//        scene.getStylesheets().add(stylesheet);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
