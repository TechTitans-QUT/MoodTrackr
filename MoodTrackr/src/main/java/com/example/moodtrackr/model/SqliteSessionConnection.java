package com.example.moodtrackr.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteSessionConnection {
    private static Connection instance = null;

    private SqliteSessionConnection() {
        String url = "jdbc:sqlite:MoodTrackr/src/main/resources/Database/sessions.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new SqliteSessionConnection();
        }
        return instance;
    }
}