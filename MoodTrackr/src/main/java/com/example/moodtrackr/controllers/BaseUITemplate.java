package com.example.moodtrackr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.example.moodtrackr.NavigationMethods.ButtonNav;

public class BaseUITemplate {
    public HBox searchbar;
    public VBox sidebar;
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

    public BaseUITemplate() {
        init();
    }

    @FXML
    protected void init() {
        try {
            System.out.println("NO Error");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

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
