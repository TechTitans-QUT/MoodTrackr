package com.example.moodtrackr.controllers;

import com.example.moodtrackr.HelloApplication;
import com.example.moodtrackr.MoodInputApplication;
import com.example.moodtrackr.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MoodInputPageController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button DashboardButton;
    @FXML
    private ListView<Session> sessionListView;

    @FXML
    protected void onAddSessionButtonClick() {
        welcomeText.setText("Add Session!");
    }
    @FXML
    protected void onEndSessionButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MoodInputApplication.class.getResource("mood-input.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MoodInputApplication.WIDTH, MoodInputApplication.HEIGHT);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    protected void onDashboardButtonClick() throws IOException {
        Stage stage = (Stage) DashboardButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);

    }
}