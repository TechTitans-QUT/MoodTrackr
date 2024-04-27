package com.example.moodtrackr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegisterController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onRegisterButtonClick() {
        welcomeText.setText("Welcome to Moodtracker Register!");
    }

    @FXML
    protected void onButton1Click() {
        welcomeText.setText("Button 1 was clicked!");
    }

    @FXML
    protected void onButton2Click() {
        welcomeText.setText("Button 2 was clicked!");
    }

    @FXML
    protected void onButton3Click() {
        welcomeText.setText("Button 3 was clicked!");
    }
}