package com.example.moodtrackr.controllers;

import com.example.moodtrackr.HelloApplication;
import com.example.moodtrackr.RegisterApplication;
import com.example.moodtrackr.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private PasswordField passwordPasswordField;
    @FXML
    private final IUserDAO userDAO;

    public LoginController() {
        userDAO = new SqliteUserDAO();
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException {
        if (!firstNameTextField.getText().isBlank() && !lastNameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            if (userDAO.verifyUser(firstNameTextField.getText(), lastNameTextField.getText(), passwordPasswordField.getText())) {
//                loginMessage.setText("Welcome!");
                // Find user id
                int id = userDAO.getUserId(firstNameTextField.getText(), lastNameTextField.getText(), passwordPasswordField.getText());
                // Find User
                User current = userDAO.getUser(id);
                Stage stage = (Stage) loginMessage.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(RegisterApplication.class.getResource("hello-view.fxml"));
                Parent root = loader.load();
                BaseUITemplate baseUITemplate = loader.getController();
                baseUITemplate.init(current);
                Scene scene = new Scene(root);
                stage.setTitle("Dashboard");
                stage.setScene(scene);
                stage.show();
            } else {
                loginMessage.setText("Invalid");
            }
        } else {
            loginMessage.setText("Mandatory fields are empty");
        }
    }

    @FXML
    public void onRegisterButtonClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterApplication.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setTitle("Register Page");
        stage.setScene(scene);
    }
}