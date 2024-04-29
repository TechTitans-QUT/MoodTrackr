package com.example.moodtrackr.controllers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeTracker {
    private LocalTime startTime;
    public boolean isTracking;


    public void startTracking() {
        // start tracking current time if there's no other trackers
        if (!isTracking) {
            startTime = LocalTime.now();
            isTracking = true;
        }
    }
    public void stopTracking() {
        isTracking = false;
    }

    public String getCurrentTime() {
        // returns the current time if there is a time tracker occurring
        if (!isTracking) {
            return "No Ongoing Session!";
        }
        LocalTime currentTime = LocalTime.now();
        long secondsPassed = startTime != null ? startTime.until(currentTime, java.time.temporal.ChronoUnit.SECONDS) : 0;
        return formatTime(secondsPassed);
    }

    private String formatTime(long seconds) {
        // format time as, hh:mm:ss
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

}