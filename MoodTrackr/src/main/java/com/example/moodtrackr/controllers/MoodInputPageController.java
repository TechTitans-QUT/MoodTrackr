package com.example.moodtrackr.controllers;

import com.example.moodtrackr.HelloApplication;
import com.example.moodtrackr.MoodInputApplication;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;



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
    @FXML
    private ImageView cryingFace;
    private TimeTracker tracker;
    private Timeline timeline;


    @FXML
    protected void onEndSessionButtonClick() throws IOException {

        startSessionButton.setDisable(false); // disable start session
        endSessionButton.setDisable(true); // enable end session

        currentTime.setText("Current Time: " + tracker.getCurrentTime());
        timeline.stop();
        tracker.stopTracking(); // stop tracking time

//        Image cryFace = new Image(HelloApplication.getResourceAsStream("@/images/icons8-loudly-crying-face-48.png"));
//        cryingFace.setImage(cryFace);

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MoodInputApplication.class.getResource("mood-input.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MoodInputApplication.WIDTH, MoodInputApplication.HEIGHT);
        stage.setScene(scene);
        stage.showAndWait();


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
        tracker = new TimeTracker();

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> onStartSession()));
    }

}