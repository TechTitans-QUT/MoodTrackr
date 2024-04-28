package com.example.moodtrackr.controllers;

import com.example.moodtrackr.HelloApplication;
import com.example.moodtrackr.MoodInputApplication;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Popup;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class MoodInputPageController {

    @FXML
    private Button DashboardButton;
    @FXML
    private Button DataButton;
    @FXML
    private Button CalendarButton;
    @FXML
    private Button SettingsButton;
    @FXML
    private Button LogOffButton;

    @FXML
    private Button startSessionButton;
    @FXML
    private Button endSessionButton;

    @FXML
    private Label currentTime;

    private TimeTracker tracker;
    private Timeline timeline;




    @FXML
    protected void onEndSessionButtonClick() throws IOException {
        startSessionButton.setDisable(false); // disable start session
        endSessionButton.setDisable(true); // enable end session

        currentTime.setText("Current Time: " + tracker.getCurrentTime());
        timeline.stop();
        tracker.stopTracking(); // stop tracking time

        Label label = new Label("This is a Popup");
        Popup popup = new Popup();

        FXMLLoader fxmlLoader = new FXMLLoader(MoodInputApplication.class.getResource("mood-input.fxml"));


    }
    @FXML
    protected void onDashboardButtonClick() throws IOException {
        Stage stage = (Stage) DashboardButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);

    }
    @FXML
    private void onStartSession() {
        if (tracker.isTracking){
            currentTime.setText("Current Time: " + tracker.getCurrentTime());
            startSessionButton.setDisable(true); // disable start session
            endSessionButton.setDisable(false); // enable end session
        }
        tracker.startTracking();

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }

    public void initialize() {
        endSessionButton.setDisable(true);

        tracker = new TimeTracker();

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> onStartSession()));

        // Create a timeline to update the time label every second

    }

}