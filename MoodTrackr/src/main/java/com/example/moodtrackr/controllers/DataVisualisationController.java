package com.example.moodtrackr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class DataVisualisationController {
    public VBox sidebarTemplate;
    public HBox bodyTemplate;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Visualiser!");
    }
}