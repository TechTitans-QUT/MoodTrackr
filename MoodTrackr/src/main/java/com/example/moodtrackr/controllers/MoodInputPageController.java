package com.example.moodtrackr.controllers;

import com.example.moodtrackr.HelloApplication;
import com.example.moodtrackr.MoodInputApplication;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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

import static com.example.moodtrackr.NavigationMethods.ButtonNav;


public class MoodInputPageController {

    @FXML
    private Button Dashboard;
    @FXML
    private Button Mood;
    @FXML
    private Button DataVisualisation;
    @FXML
    private Button Calendar;
    @FXML
    private Button Settings;
    @FXML
    private Button logout;
    @FXML
    private Button startSessionButton;
    @FXML
    private Button endSessionButton;
    @FXML
    private ToolBar moodToolBar;
    @FXML
    public Label currentTime;
    @FXML
    private CheckBox mood1;
    @FXML
    private CheckBox mood2;
    @FXML
    private CheckBox mood3;
    @FXML
    private CheckBox mood4;
    @FXML
    private CheckBox mood5;
    @FXML
    private CheckBox mood6;
    @FXML
    private CheckBox mood7;

    private TimeTracker tracker;
    private Timeline timeline;

    private int mood;
    private boolean checked;


    @FXML
    protected void onDashboardButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "hello-view.fxml");
    }
    @FXML
    protected void onMoodButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml");
    }
    @FXML
    protected void onDataVisualisationButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "data-visualisation-page.fxml");
    }
    @FXML
    protected void onCalendarButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml");
    }
    @FXML
    protected void onSettingsButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml");
    }
    @FXML
    protected void onLogoutButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "login-view.fxml");
    }


    @FXML
    private void onEndSessionButtonClick() {
        startSessionButton.setDisable(false); // disable start session
        endSessionButton.setDisable(true); // enable end session
        moodToolBar.setDisable(true); // enable mood select

        // uncheck boxes
        mood1.setSelected(false);
        mood2.setSelected(false);
        mood3.setSelected(false);
        mood4.setSelected(false);
        mood5.setSelected(false);
        mood6.setSelected(false);
        mood7.setSelected(false);

        // disable all check boxes
        mood1.setDisable(true);
        mood2.setDisable(true);
        mood3.setDisable(true);
        mood4.setDisable(true);
        mood5.setDisable(true);
        mood6.setDisable(true);
        mood7.setDisable(true);

        currentTime.setText("Current Time: " + tracker.getCurrentTime());
        timeline.stop();
        tracker.stopTracking(); // stop tracking time
    }
    @FXML
    private void onStartSession() {
        if (tracker.isTracking){
            currentTime.setText("Current Time: " + tracker.getCurrentTime());
            startSessionButton.setDisable(true); // disable start session
            //endSessionButton.setDisable(false); // enable end session
            moodToolBar.setDisable(false); // enable mood select

        }
        else{
            mood1.setDisable(false);
            mood2.setDisable(false);
            mood3.setDisable(false);
            mood4.setDisable(false);
            mood5.setDisable(false);
            mood6.setDisable(false);
            mood7.setDisable(false);
        }


        tracker.startTracking();

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    @FXML
    private void onMood1Check() {
        checked = mood1.isSelected();
        mood = 1;
        endSessionButton.setDisable(!checked);

        // disable other check boxes
        //mood1.setDisable(checked);
        mood2.setDisable(checked);
        mood3.setDisable(checked);
        mood4.setDisable(checked);
        mood5.setDisable(checked);
        mood6.setDisable(checked);
        mood7.setDisable(checked);

    }
    @FXML
    private void onMood2Check() {
        checked = mood2.isSelected();
        mood = 2;


        endSessionButton.setDisable(!checked);
        mood1.setDisable(checked);
        //mood2.setDisable(checked);
        mood3.setDisable(checked);
        mood4.setDisable(checked);
        mood5.setDisable(checked);
        mood6.setDisable(checked);
        mood7.setDisable(checked);

    }
    @FXML
    private void onMood3Check() {
        checked = mood3.isSelected();
        mood = 3;

        endSessionButton.setDisable(!checked);
        mood1.setDisable(checked);
        mood2.setDisable(checked);
       //mood3.setDisable(checked);
        mood4.setDisable(checked);
        mood5.setDisable(checked);
        mood6.setDisable(checked);
        mood7.setDisable(checked);

    }
    @FXML
    private void onMood4Check() {
        checked = mood4.isSelected();
        mood = 4;

        endSessionButton.setDisable(!checked);
        mood1.setDisable(checked);
        mood2.setDisable(checked);
        mood3.setDisable(checked);
        //mood4.setDisable(checked);
        mood5.setDisable(checked);
        mood6.setDisable(checked);
        mood7.setDisable(checked);
    }
    @FXML
    private void onMood5Check() {
        checked = mood5.isSelected();
        mood = 5;

        endSessionButton.setDisable(!checked);
        mood1.setDisable(checked);
        mood2.setDisable(checked);
        mood3.setDisable(checked);
        mood4.setDisable(checked);
        //mood5.setDisable(checked);
        mood6.setDisable(checked);
        mood7.setDisable(checked);

    }
    @FXML
    private void onMood6Check() {
        checked = mood6.isSelected();
        mood = 6;

        endSessionButton.setDisable(!checked);
        mood1.setDisable(checked);
        mood2.setDisable(checked);
        mood3.setDisable(checked);
        mood4.setDisable(checked);
        mood5.setDisable(checked);
        //mood6.setDisable(checked);
        mood7.setDisable(checked);

    }
    @FXML
    private void onMood7Check() {
        checked = mood7.isSelected();
        mood = 7;

        endSessionButton.setDisable(!checked);
        mood1.setDisable(checked);
        mood2.setDisable(checked);
        mood3.setDisable(checked);
        mood4.setDisable(checked);
        mood5.setDisable(checked);
        mood6.setDisable(checked);
        //mood7.setDisable(checked);

    }

    public void initialize() {

        tracker = new TimeTracker();

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> onStartSession()));
    }

}