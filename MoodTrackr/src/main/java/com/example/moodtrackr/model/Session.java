package com.example.moodtrackr.model;

import javafx.animation.Timeline;

import java.time.LocalDateTime;

public class Session {
    private int id;
    private Timeline time;
    private int mood;
    private LocalDateTime localTime;
    private int status;

    public Session(Timeline time, int mood, LocalDateTime localTime, int status){
        this.time = time;
        this.mood = mood;
        this.localTime = localTime;
        this.status = status;
    }

    public int getID() {return id;}
    public Timeline getTime() {return time;}
    public int getMood() {return mood;}
    public LocalDateTime getLocalTime() {return localTime;}
    public int getStatus() {return status;}

    public void setID(int id) { this.id = id; }
    public void setTime(Timeline time) { this.time = time; }
    public void setMood(int mood) {this.mood = mood;}
    public void setLocalTime(LocalDateTime localTime) {this.localTime = localTime;}
    public void setStatus(int status) {this.status = status;}


}
