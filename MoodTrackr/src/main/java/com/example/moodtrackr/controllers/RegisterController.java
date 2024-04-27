package com.example.moodtrackr.controllers;

import com.example.moodtrackr.RegisterApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {
    @FXML
    private Label welcomeText;
    @FXML
    private CheckBox agreeCheckBox;
    @FXML
    private Button registerButton;

    @FXML
    protected void onAgreeCheckBoxClick() {
        boolean accepted = agreeCheckBox.isSelected();
        registerButton.setDisable(!accepted);
    }

    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onRegisterButtonClick() {
        welcomeText.setText("Register Button clicked!");
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), RegisterApplication.WIDTH, RegisterApplication.HEIGHT);
        String stylesheet = Objects.requireNonNull(RegisterApplication.class.getResource("stylesheet.css")).toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }
}