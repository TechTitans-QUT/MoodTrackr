package com.example.moodtrackr.controllers;

import com.example.moodtrackr.HelloApplication;
import com.example.moodtrackr.RegisterApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.moodtrackr.NavigationMethods.ButtonNav;

public class BaseUITemplate {
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
}
