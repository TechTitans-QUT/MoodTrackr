package com.example.moodtrackr.controllers;

import com.example.moodtrackr.model.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

import javafx.scene.control.Label;
import javafx.util.Duration;

import static com.example.moodtrackr.NavigationMethods.ButtonNav;


public class MoodInputPageController {
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
    private Button startSessionButton;
    @FXML
    private Button endSessionButton;
    @FXML
    private ToolBar moodToolBar;
    @FXML
    public Label currentTime;
    @FXML
    private CheckBox mood1;
    @FXML
    private CheckBox mood2;
    @FXML
    private CheckBox mood3;
    @FXML
    private CheckBox mood4;
    @FXML
    private CheckBox mood5;
    @FXML
    private CheckBox mood6;
    @FXML
    private CheckBox mood7;

    @FXML
    private ListView<Session> sessionsListView;
//    private ISessionDAO sessionDAO;
    @FXML
    private SessionManager sessionManager;
    @FXML
    TextField sessionTimeTestField;
    @FXML
    TextField moodTextField;
    @FXML
    TextField localTimeTextField;
    @FXML
    TextField statusTextField;
    @FXML
    private VBox sessionContainer;

    public MoodInputPageController() {

//        sessionDAO = (new MockSessionDAO());
//        sessionManager = new SessionManager(new MockSessionDAO());
        sessionManager = new SessionManager(new SqliteSessionDAO());
    }

    private TimeTracker tracker;
    private Timeline timeline;

    public String sessionTime;
    public String  mood;
    public String localTime;
    public int status;
    private boolean checked;

    private ListCell<Session> renderCell(ListView<Session> sessionsListView) {
        return new ListCell<>() {

            @Override
            protected void updateItem(Session session, boolean empty) {
                super.updateItem(session, empty);

                if (empty || session == null || session.getAll() == null) {
                    setText(null);

                } else {
                    setText(session.getAll());
                }
            }
        };
    }
    /*
    Checks/unchecks the mood check boxes
     */
    private void setMoodCheckBoxes(boolean setter){
        mood1.setSelected(setter);
        mood2.setSelected(setter);
        mood3.setSelected(setter);
        mood4.setSelected(setter);
        mood5.setSelected(setter);
        mood6.setSelected(setter);
        mood7.setSelected(setter);
    }

    /*
    Disables/Enables the mood checkboxes
     */
    private void disableMoodCheckBoxes(boolean setter){
        mood1.setDisable(setter);
        mood2.setDisable(setter);
        mood3.setDisable(setter);
        mood4.setDisable(setter);
        mood5.setDisable(setter);
        mood6.setDisable(setter);
        mood7.setDisable(setter);
    }

//    private void syncSessions() {
//        sessionsListView.getItems().clear();
//
////        // --- New code to search for contacts ---
////        String query = searchTextField.getText();
////        List<Contact> contacts = contactManager.searchContacts(query);
//
//        // --- End of new code ---
//        boolean hasSession = !session.isEmpty();
//        if (hasContact) {
//            contactsListView.getItems().addAll(contacts);
//        }
//        // Show / hide based on whether there are contacts
//        contactContainer.setVisible(hasContact);
//    }

    private void syncSessions() {
        sessionsListView.getItems().clear();
//        sessionsListView.getItems().addAll(sessionDAO.getAllSessions());
        List<Session> sessions = sessionManager.getAllSessions();
        sessionsListView.getItems().addAll(sessions);
    }

    @FXML
    protected void onDashboardButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "hello-view.fxml");
    }
    @FXML
    protected void onMoodButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml");
    }
    @FXML
    protected void onDataVisualisationButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "data-visualisation-page.fxml");
    }
    @FXML
    protected void onCalendarButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml");
    }
    @FXML
    protected void onSettingsButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "mood-input-page.fxml");
    }
    @FXML
    protected void onLogoutButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource(); // Get the button that triggered the event
        ButtonNav(button, "login-view.fxml");
    }


    @FXML
    private void onEndSessionButtonClick() {
        startSessionButton.setDisable(false);
        endSessionButton.setDisable(true);
        moodToolBar.setDisable(true);
        // disable and uncheck boxes
        disableMoodCheckBoxes(true);
        setMoodCheckBoxes(false);


        currentTime.setText("Current Time: " + tracker.getCurrentTime());
        timeline.stop();
        sessionTime = tracker.getCurrentTime();

        tracker.stopTracking(); // stop tracking time

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        localTime = dtf.format(now); // get local time and date

        Session newSession = new Session(sessionTime, mood, localTime, status);
        sessionManager.addSession(newSession);
//        sessionDAO.addSession(newSession);
        syncSessions();

    }
    @FXML
    private void onStartSession() {
        if (tracker.isTracking){
            currentTime.setText("Current Time: " + tracker.getCurrentTime());
            startSessionButton.setDisable(true); // disable start session
            //endSessionButton.setDisable(false); // enable end session
            moodToolBar.setDisable(false); // enable mood select
        }
        else{
            setMoodCheckBoxes(false);
            disableMoodCheckBoxes(false);
        }

        tracker.startTracking();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    @FXML
    private void onMood1Check() {
        checked = mood1.isSelected();
        mood = "Very Sad";
        endSessionButton.setDisable(!checked);

        // disable other check boxes
        //mood1.setDisable(checked);
        disableMoodCheckBoxes(checked);
        mood1.setDisable(false);

    }
    @FXML
    private void onMood2Check() {
        checked = mood2.isSelected();
        mood = "Sad";

        endSessionButton.setDisable(!checked);
        disableMoodCheckBoxes(checked);
        mood2.setDisable(false);

    }
    @FXML
    private void onMood3Check() {
        checked = mood3.isSelected();
        mood = "Slightly Sad";

        endSessionButton.setDisable(!checked);
        disableMoodCheckBoxes(checked);
        mood3.setDisable(false);

    }
    @FXML
    private void onMood4Check() {
        checked = mood4.isSelected();
        mood = "Neutral";

        endSessionButton.setDisable(!checked);
        disableMoodCheckBoxes(checked);
        mood4.setDisable(false);
    }
    @FXML
    private void onMood5Check() {
        checked = mood5.isSelected();
        mood = "Slightly Happy";

        endSessionButton.setDisable(!checked);
        disableMoodCheckBoxes(checked);
        mood5.setDisable(false);

    }
    @FXML
    private void onMood6Check() {
        checked = mood6.isSelected();
        mood = "Happy";

        endSessionButton.setDisable(!checked);
        disableMoodCheckBoxes(checked);
        mood6.setDisable(false);

    }
    @FXML
    private void onMood7Check() {
        checked = mood7.isSelected();
        mood = "Very Happy";

        endSessionButton.setDisable(!checked);
        disableMoodCheckBoxes(checked);
        mood7.setDisable(false);

    }
    @FXML
    public void initialize() {
        tracker = new TimeTracker();
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> onStartSession()));
        sessionsListView.setCellFactory(this::renderCell);
        syncSessions();
    }

}