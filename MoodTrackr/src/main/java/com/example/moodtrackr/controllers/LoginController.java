package com.example.moodtrackr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}
