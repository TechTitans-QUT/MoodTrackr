package com.example.moodtrackr.controllers;

import com.example.moodtrackr.GlobalData;
import com.example.moodtrackr.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.example.moodtrackr.NavigationMethods.ButtonNav;
import static com.example.moodtrackr.NavigationMethods.NewButtonNav;

public class BaseUITemplate {
    public HBox searchbar;
    public VBox sidebar;
    @FXML
    private Button Dashboard;
    @FXML
    private Button Mood;
    @FXML
    private Button DataVisualisation;
    @FXML
    private Button Calendar;
    @FXML
    private Button Settings;
    @FXML
    private Button logout;
    @FXML
    private Label titleLabel;
    @FXML
    private MenuButton menuButton;
    private User currentAccount;

    @FXML
    public void init(User current) {
        currentAccount = current;
        titleLabel.setText("Welcome " + current.getFirstName());
        menuButton.setText(current.getFirstName() + " " + current.getLastName());
    }

    @FXML
    protected void onDashboardButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
//        ButtonNav(button, "hello-view.fxml");
        User currentAccount = GlobalData.getInstance().getYourObject();
        NewButtonNav(button, "hello-view.fxml", currentAccount);
    }
    @FXML
    protected void onMoodButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        User currentAccount = GlobalData.getInstance().getYourObject();
        NewButtonNav(button, "mood-input-page.fxml", currentAccount);
    }
    @FXML
    protected void onDataVisualisationButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        User currentAccount = GlobalData.getInstance().getYourObject();
        NewButtonNav(button, "data-visualisation-page.fxml", currentAccount);
    }
    @FXML
    protected void onCalendarButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml", currentAccount);
    }
    @FXML
    protected void onSettingsButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml", currentAccount);
    }
    @FXML
    protected void onLogoutButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "login-view.fxml", currentAccount);
    }
}
