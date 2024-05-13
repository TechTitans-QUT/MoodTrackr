package com.example.moodtrackr.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * A public class establish the connection to the SQL database.
 */
public class SqliteConnection {
    private static Connection instance = null;

    private SqliteConnection() {
        String url = "jdbc:sqlite:MoodTrackr/src/main/resources/Database/users.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }
}