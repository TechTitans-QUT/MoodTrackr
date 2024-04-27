package com.example.moodtrackr.controllers;

import com.example.moodtrackr.RegisterApplication;
import com.example.moodtrackr.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private IUserDAO userDAO;

    public LoginController() {
        userDAO = new SqliteUserDAO();
    }

    @FXML
    public void onLoginButtonClick(ActionEvent e) {
        if (!firstNameTextField.getText().isBlank() && !lastNameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            if (userDAO.verifyUser(firstNameTextField.getText(), lastNameTextField.getText(), passwordTextField.getText())) {
                loginMessage.setText("Welcome!");
            } else {
                loginMessage.setText("Invalid");
            }
        } else {
            loginMessage.setText("Mandatory filed are empty");
        }
    }

    @FXML
    public void onRegisterButtonClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterApplication.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), RegisterApplication.WIDTH, RegisterApplication.HEIGHT);
        stage.setTitle("Register Page");
        stage.setScene(scene);
    }
}