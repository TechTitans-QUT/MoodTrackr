package com.example.moodtrackr.controllers;
import com.example.moodtrackr.GlobalData;

import com.example.moodtrackr.Session;
import com.example.moodtrackr.model.SessionManager;
import com.example.moodtrackr.model.SqliteSessionDAO;
import com.example.moodtrackr.model.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.moodtrackr.NavigationMethods.ButtonNav;

public class MoodInputPageController {
    @FXML
    private Button startSessionButton;
    @FXML
    TableView <Session> sessionTableView;
    @FXML
    TableColumn <Session, String> moodColumn;
    @FXML
    TableColumn <Session, String> sessionTimeColumn;
    @FXML
    TableColumn <Session, String> localTimeColumn;
    @FXML
    TableColumn <Session, String> statusColumn;
    ObservableList<Session> observableSessions;

    @FXML
    private SessionManager sessionManager;

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

    private TimeTracker tracker;
    private Timeline timeline;

    public String sessionTime;
    public String  mood;
    public String localTime;
    public String status;
    public int currentID;
    private boolean checked;
    private User currentAccount;

    public MoodInputPageController() {

//        sessionDAO = (new MockSessionDAO());
//        sessionManager = new SessionManager(new MockSessionDAO());
        sessionManager = new SessionManager(new SqliteSessionDAO());
    }

    /**
     * Checks/unchecks every mood check-box
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

    /**
     * Disables/Enables every mood check-box
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
    /**
     * Synchronizes the sessions list view with the sessions stored in the database.
     */
    private void tableLoad() {
        observableSessions = FXCollections.observableArrayList();

        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:MoodTrackr/src/main/resources/Database/sessions.db");
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM sessions");

            while(rs.next()){
                observableSessions.add(new Session(
                        rs.getString("sessionTime"),
                        rs.getString("mood"),
                        rs.getString("localTime"),
                        rs.getString("status"),
                        rs.getInt("id")
                        ));
            }
            sessionTableView.setItems(observableSessions);

            rs.close();
            sta.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // Initialise a new time tracker and renders and
        // synchronises the cells in the listview with data in the database
        tracker = new TimeTracker();
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> onStartSession()));

        moodColumn.setCellValueFactory(new PropertyValueFactory<>("mood"));
        sessionTimeColumn.setCellValueFactory(new PropertyValueFactory<>("sessionTime"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        localTimeColumn.setCellValueFactory(new PropertyValueFactory<>("localTime"));
        tableLoad();
    }
    /**
     * Code to set the events of when the End Session button is clicked.
     *
     */
    @FXML
    private void onEndSessionButtonClick() {
        startSessionButton.setDisable(false); // enables start session button
        endSessionButton.setDisable(true); // disables end session button
        moodToolBar.setDisable(true); // disables the mood input tool bar

        // disable and uncheck boxes
        disableMoodCheckBoxes(true);
        setMoodCheckBoxes(false);

        // sets the currentTime to the last instance of tracker, then stops the tracker and stores it in sessionTime
        currentTime.setText("Current Time: " + tracker.getCurrentTime());
        timeline.stop();
        sessionTime = tracker.getCurrentTime();

        tracker.stopTracking(); // stop tracking time

        // gets the current local time and date of the user's system, and stores it in localTime
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        localTime = dtf.format(now); // get local time and date

        status = "0"; // set status
        currentAccount = GlobalData.getInstance().getYourObject();
        currentID = currentAccount.getId();
        System.out.println("CURRRENT ID:" + currentID);
        // creates a new session instance and syncs the data to the database
        Session newSession = new Session(sessionTime, mood, localTime, status, currentID);
        sessionManager.addSession(newSession);
//        sessionDAO.addSession(newSession);
        tableLoad();

    }

    /**
     * Code to handle events when the start session button is clicked
     */
    @FXML
    private void onStartSession() {
        // if the tracker is started, display the current time on the tool bar above the listview

        if (tracker.isTracking){
            currentTime.setText("Current Time: " + tracker.getCurrentTime());
            startSessionButton.setDisable(true); // disable start session
            //endSessionButton.setDisable(false); // enable end session
            moodToolBar.setDisable(false); // enable mood select
        }
        // enable the mood check boxes
        else{
            setMoodCheckBoxes(false);
            disableMoodCheckBoxes(false);
        }

        tracker.startTracking(); // start tracking time
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    @FXML
    // The methods below handle the evenets of each mood check boxes being pressed
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

}