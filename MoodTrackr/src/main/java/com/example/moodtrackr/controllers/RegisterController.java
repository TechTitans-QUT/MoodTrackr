package com.example.moodtrackr.controllers;

import com.example.moodtrackr.RegisterApplication;
import com.example.moodtrackr.model.IUserDAO;
import com.example.moodtrackr.model.SqliteUserDAO;
import com.example.moodtrackr.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private ListView<User> usersListView;
    @FXML
    private IUserDAO userDAO;
    @FXML
    private Button registerButton;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;

    public RegisterController() {
        userDAO = new SqliteUserDAO();
    }

    /**
     * Programmatically selects a user in the list view and
     * updates the text fields with the user's information.
     * @param user The contact to select.
     */
    private void selectUser(User user) {
        usersListView.getSelectionModel().select(user);
        firstNameTextField.setText(user.getFirstName());
        lastNameTextField.setText(user.getLastName());
        emailTextField.setText(user.getEmail());
        passwordTextField.setText(user.getPassword());
    }

    /**
     * Renders a cell in the users list view by setting the text to the user's full name.
     * @param userListView The list view to render the cell for.
     * @return The rendered cell.
     */
    private ListCell<User> renderCell(ListView<User> userListView) {
        return new ListCell<>() {
            /**
             * Handles the event when a user is selected in the list view.
             * @param mouseEvent The event to handle.
             */
            private void onUserSelected(MouseEvent mouseEvent) {
                ListCell<User> clickedCell = (ListCell<User>) mouseEvent.getSource();
                // Get the selected contact from the list view
                User selectedUser = clickedCell.getItem();
                if (selectedUser != null) selectUser(selectedUser);
            }
            /**
             * Updates the item in the cell by setting the text to the contact's full name.
             * @param user The contact to update the cell with.
             * @param empty Whether the cell is empty.
             */
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                // If the cell is empty, set the text to null, otherwise set it to the contact's full name
                if (empty || user == null || user.getFullName() == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onUserSelected);
                } else {
                    setText(user.getFullName());
                }
            }
        };
    }

    @FXML
    public void initialize() {
        usersListView.setCellFactory(this::renderCell);
        usersListView.getItems().addAll(userDAO.getAllUsers());

        // Start Register
        // Default values for a new contact
        final String DEFAULT_FIRST_NAME = "";
        final String DEFAULT_LAST_NAME = "";
        final String DEFAULT_EMAIL = "";
        final String DEFAULT_PASSWORD = "";
        User newUser = new User(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL, DEFAULT_PASSWORD);
        // Add the new contact to the database
        userDAO.addUser(newUser);
        syncUsers();
        // Select the new contact in the list view
        // and focus the first name text field
        selectUser(newUser);
        firstNameTextField.requestFocus();
    }

    @FXML
    private void onRegisterButtonClick() {
        // Check for mandatory inputs
        if (!firstNameTextField.getText().isBlank() && !lastNameTextField.getText().isBlank() &&
                !passwordTextField.getText().isBlank() && !emailTextField.getText().isBlank()) {
            // Get the selected contact from the list view
            User selectedUser = usersListView.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                selectedUser.setFirstName(firstNameTextField.getText());
                selectedUser.setLastName(lastNameTextField.getText());
                selectedUser.setEmail(emailTextField.getText());
                selectedUser.setPassword(passwordTextField.getText());
                userDAO.updateUser(selectedUser);
                syncUsers();
            } else {
                errorLabel.setText("Missing Mandatory field");
            }
        }
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), RegisterApplication.WIDTH, RegisterApplication.HEIGHT);
        stage.setTitle("Login Page");
        stage.setScene(scene);
    }

    /**
     * Synchronizes the contacts list view with the contacts in the database.
     */
    private void syncUsers() {
        usersListView.getItems().clear();
        usersListView.getItems().addAll(userDAO.getAllUsers());
    }

    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) usersListView.getScene().getWindow();
        stage.close();
    }
}