package com.example.moodtrackr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegisterController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Moodtracker Register!");
    }
}