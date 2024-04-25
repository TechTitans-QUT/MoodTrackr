package com.example.moodtrackr.controller;

import com.example.moodtrackr.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.List;
public class MoodInputController {
    @FXML
    private Label welcomeText;
    @FXML
    private ListView<Session> sessionListView;

    @FXML
    protected void onAddSessionButtonClick() {
        welcomeText.setText("Add Session!");
    }
    @FXML
    protected void onEndSession() {
        welcomeText.setText("End Session!");
    }
}