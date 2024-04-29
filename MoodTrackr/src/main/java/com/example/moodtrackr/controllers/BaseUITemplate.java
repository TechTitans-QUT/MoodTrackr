package com.example.moodtrackr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;

import static com.example.moodtrackr.NavigationMethods.ButtonNav;

public class BaseUITemplate {
    @FXML
    private Button DashboardButton;
    @FXML
    private Button MoodButton;
    @FXML
    private Button DataVisualisationButton;
    @FXML
    private Button CalendarButton;
    @FXML
    private Button SettingsButton;
    @FXML
    protected void onDashboardButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml");
    }
    @FXML
    protected void onMoodButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml");
    }
    @FXML
    protected void onDataVisualisationButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml");
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
}
