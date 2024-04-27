package com.example.moodtrackr.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoggedInController {
    @FXML
    private Button logoutButton;

    @FXML
    private Label welcomeLabel;

    public void init() {
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }

    public void setInfo(String username) {
        welcomeLabel.setText("Welcome " + username + "!");
    }


}
