package com.example.moodtrackr.controllers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A public class that contains methods necessary for tracking the time like a timer.
 */
public class TimeTracker {
    private LocalTime startTime;
    public boolean isTracking;

    /**
     * Starts tracking time if there are no trackers already doing so
     */
    public void startTracking() {
        // start tracking current time if there's no other trackers
        if (!isTracking) {
            startTime = LocalTime.now();
            isTracking = true;
        }
    }

    /**
     * Stops tracking time
     */
    public void stopTracking() {
        isTracking = false;
    }

    /**
     * Gets the current time from the tracker
     *
     * @return the current time, formatted in "%02d:%02d:%02d"
     */
    public String getCurrentTime() {
        // returns the current time if there is a time tracker occurring
        if (!isTracking) {
            return "No Ongoing Session!";
        }
        LocalTime currentTime = LocalTime.now();
        long secondsPassed = startTime != null ? startTime.until(currentTime, java.time.temporal.ChronoUnit.SECONDS) : 0;
        return formatTime(secondsPassed);
    }

    /**
     * FMethod to format time in: "%02d:%02d:%02d"
     * @param seconds, the time collected by the tracker given in seconds
     * @return the time, formatted in "%02d:%02d:%02d"
     */
    private String formatTime(long seconds) {
        // format time as, hh:mm:ss
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

}