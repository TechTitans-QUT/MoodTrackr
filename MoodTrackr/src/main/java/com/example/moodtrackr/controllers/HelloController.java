package com.example.moodtrackr.controllers;

import com.example.moodtrackr.HelloApplication;
import com.example.moodtrackr.RegisterApplication;
import com.example.moodtrackr.model.SqliteUserDAO;
import com.example.moodtrackr.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Button MoodInputButton;
    @FXML
    private Button logOutButton;

    // add other buttons for nav to other pages
    @FXML
    protected void onMoodInputButtonClick() throws IOException {
        Stage stage = (Stage) MoodInputButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mood-input-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}