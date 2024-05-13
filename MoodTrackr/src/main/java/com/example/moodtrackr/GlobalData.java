package com.example.moodtrackr;

import com.example.moodtrackr.model.User;

public class GlobalData {
    private static GlobalData instance;
    private User yourObject;

    private GlobalData() {
        // Private constructor to prevent instantiation
    }

    public static GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    public User getYourObject() {
        return yourObject;
    }

    public void setYourObject(User yourObject) {
        this.yourObject = yourObject;
    }
}