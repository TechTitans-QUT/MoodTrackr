package com.example.moodtrackr.controllers;

import com.example.moodtrackr.MainApplication;
import com.example.moodtrackr.model.IUserDAO;
import com.example.moodtrackr.model.SqliteUserDAO;
import com.example.moodtrackr.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private ListView<User> usersListView;
    @FXML
    private final IUserDAO userDAO;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private CheckBox debugCheckBox;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editConfirmButton;
    @FXML
    private VBox logoContainer;

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
        passwordPasswordField.setText(user.getPassword());
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
    }

    @FXML
    private void onRegisterButtonClick() {
        // Check for mandatory inputs
        if (!firstNameTextField.getText().isBlank() && !lastNameTextField.getText().isBlank() &&
                !passwordPasswordField.getText().isBlank() && !emailTextField.getText().isBlank()) {

            if (passwordPasswordField.getText().equals(confirmPasswordField.getText())) {
                errorLabel.setText("You are set");
                User newUser = new User(firstNameTextField.getText(), lastNameTextField.getText(),
                        emailTextField.getText(), passwordPasswordField.getText());
                userDAO.addUser(newUser);
                errorLabel.setText("User Registered Successfully!");
                syncUsers();
            } else {
                errorLabel.setText("Password does not match");
            }
        } else {
            errorLabel.setText("Missing Mandatory field");
        }
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
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

    @FXML
    protected void onDebug() {
        boolean accepted = debugCheckBox.isSelected();
        usersListView.setVisible(accepted);
        deleteButton.setVisible(accepted);
        editConfirmButton.setVisible(accepted);
        logoContainer.setVisible(!accepted);
    }

    @FXML
    private void onDelete() {
        // Get the selected contact from the list view
        User selectedContact = usersListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            userDAO.deleteUser(selectedContact);
            syncUsers();
        }
    }

    @FXML
    private void onEditConfirm() {
        // Get the selected contact from the list view
        User selectedUser = usersListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            selectedUser.setFirstName(firstNameTextField.getText());
            selectedUser.setLastName(lastNameTextField.getText());
            selectedUser.setEmail(emailTextField.getText());
            selectedUser.setPassword(passwordPasswordField.getText());
            userDAO.updateUser(selectedUser);
            syncUsers();
        }
    }
}