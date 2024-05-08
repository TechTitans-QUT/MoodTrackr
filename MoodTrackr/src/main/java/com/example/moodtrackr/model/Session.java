package com.example.moodtrackr.model;

public class Session {
    private int id;
    private String sessionTime;
    private String mood;
    private String localTime;
    private int status;

    public Session(String sessionTime, String mood, String localTime, int status){
        this.sessionTime = sessionTime;
        this.mood = mood;
        this.localTime = localTime;
        this.status = status;
    }

    public int getID() {return id;}
    public String getSessionTime() {return sessionTime;}
    public String getMood() {return mood;}
    public String getLocalTime() {return localTime;}
    public int getStatus() {return status;}
    public String getMoodAtDate() {
        return mood + " " + localTime;
    }
    public String getAll() {
        return mood + " " + sessionTime + " " + status + " " + localTime ;
    }


    public void setID(int id) { this.id = id; }
    public void setSessionTime(String sessionTime) { this.sessionTime = sessionTime; }
    public void setMood(String mood) {this.mood = mood;}
    public void setLocalTime(String localTime) {this.localTime = localTime;}
    public void setStatus(int status) {this.status = status;}



}
