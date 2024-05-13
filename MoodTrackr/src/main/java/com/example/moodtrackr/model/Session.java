package com.example.moodtrackr.model;

/**
 * Class that contains getters and setters for each session data
 */
public class Session {
    private int id;
    private String sessionTime;
    private String mood;
    private String localTime;
    private int status;

    /**
     * Initialises a new Session object
     *
     * @param sessionTime, current session Time
     * @param mood, the mood input
     * @param localTime, the local time the data was collected, formatted in "%02d:%02d:%02d"
     * @param status, an integer which shows whether a session is ongoing (1: ongoing, 0: ended)
     */
    public Session(String sessionTime, String mood, String localTime, int status){
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
    public int getStatus() {return status;}
    public String getMoodAtDate() {
        return mood + " " + localTime;
    }

    /**
     * Gets all session data
     * @return all session data formatted as a string
     */
    public String getAll() {
        return mood + " " + sessionTime + " " + status + " " + localTime ;
    }

    /*
        The necessary setters for each session data
         */
    public void setID(int id) { this.id = id; }
    public void setSessionTime(String sessionTime) { this.sessionTime = sessionTime; }
    public void setMood(String mood) {this.mood = mood;}
    public void setLocalTime(String localTime) {this.localTime = localTime;}
    public void setStatus(int status) {this.status = status;}



}
