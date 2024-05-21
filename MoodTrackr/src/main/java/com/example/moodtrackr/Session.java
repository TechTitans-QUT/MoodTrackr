package com.example.moodtrackr;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class that contains getters and setters for each session data
 */
public class Session {
    private int id;
    private String sessionTime;
    private String mood;
    private String localTime;
    private String status;

    /**
     * Initialises a new Session object
     *
     */
    public Session(String sessionTime, String mood, String localTime, String status){
        this.sessionTime = sessionTime;
        this.mood = mood;
        this.localTime = localTime;
        this.status = status;
    }

    /*
    The necessary getters for each session data
     */
    public int getID() {return id;}
    public String getSessionTime() {return sessionTime;}
    public String getMood() {return mood;}
    public String getLocalTime() {return localTime;}
    public String getStatus() {return status;}


    /*
        The necessary setters for each session data
    */
    public void setID(int id) { this.id = id; }
    public void setSessionTime(String sessionTime) { this.sessionTime = sessionTime; }
    public void setMood(String mood) {this.mood = mood;}
    public void setLocalTime(String localTime) {this.localTime = localTime;}
    public void setStatus(String status) {this.status = status;}


    /*
        Property values for each session data
     */
//    public StringProperty sessionTimeProperty() { return sessionTime; }
//    public StringProperty moodProperty() { return mood; }
//    public StringProperty localTimeProperty() { return localTime; }
//    public StringProperty statusProperty() { return status; }

}
