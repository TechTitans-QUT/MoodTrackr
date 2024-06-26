package com.example.moodtrackr.controllers;

import com.example.moodtrackr.GlobalData;
import com.example.moodtrackr.model.User;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

import static com.example.moodtrackr.NavigationMethods.ButtonNav;
import static com.example.moodtrackr.NavigationMethods.NewButtonNav;

public class BaseUITemplate {
    public HBox searchbar;
    @FXML
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
    private Button Logout;
    @FXML
    private Button logout;
    @FXML
    private Label titleLabel;
    @FXML
    private MenuButton menuButton;
    @FXML
    private Button side;
    private User currentAccount;
    private Boolean closed = true;

    @FXML
    public void init(User current) {
        currentAccount = current;
        titleLabel.setText("Welcome " + current.getFirstName());
        menuButton.setText(current.getFirstName() + " " + current.getLastName());
    }

    @FXML
    protected void onDashboardButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        NewButtonNav(button, "dashboard-view.fxml");
    }
    @FXML
    protected void onMoodButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        NewButtonNav(button, "mood-input-page.fxml");
    }
    @FXML
    protected void onDataVisualisationButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        NewButtonNav(button, "data-visualisation-page.fxml");
    }
    @FXML
    protected void onCalendarButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        NewButtonNav(button, "template-page.fxml");
    }
    @FXML
    protected void onSettingsButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        NewButtonNav(button, "template-page.fxml");
    }
    @FXML
    protected void onLogoutButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "login-view.fxml");
    }

    @FXML
    protected void onSide() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(logout);

        slide.setToX(0);
        slide.play();

        if (closed) {
            sidebar.setTranslateX(-120);
            closed = false;
            side.setText(">");
        } else {
            sidebar.setTranslateX(0);
            closed = true;
            side.setText("<");
        }

        Dashboard.setVisible(closed);
        Mood.setVisible(closed);
        DataVisualisation.setVisible(closed);
        Calendar.setVisible(closed);
        Settings.setVisible(closed);
        Logout.setVisible(closed);
    }
}