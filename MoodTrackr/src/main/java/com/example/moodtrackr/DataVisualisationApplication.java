package com.example.moodtrackr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;

import java.io.IOException;
public class DataVisualisationApplication extends Application {
    public static final String Title = "Data Visualisation";
    public static final int height = 360;
    public static final int width = 640;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterApplication.class.getResource("data-visualisation.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle(Title);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
