package com.example.moodtrackr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DataVisualisationController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Visualiser!");
    }
}