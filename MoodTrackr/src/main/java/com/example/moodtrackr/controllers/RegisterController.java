package com.example.moodtrackr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegisterController {
    @FXML
    private Label welcomeText;
    @FXML
    protected void onCancel() {
        welcomeText.setText("Cancel Button clicked!");
    }

    @FXML
    protected void onRegisterButtonClick() {
        welcomeText.setText("Register Button clicked!");
    }

    @FXML
    protected void onLoginButtonClick() {
        welcomeText.setText("Login Button clicked!");
    }
}