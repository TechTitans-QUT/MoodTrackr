package com.example.moodtrackr.model;

import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A public class that contains methods that enable the ability to
 * add, update and delete data from a sessions database file
 */
public class SqliteSessionDAO implements ISessionDAO {
    private final Connection connection;
    public SqliteSessionDAO() {
        connection = SqliteSessionConnection.getInstance();
        createTable();
//        insertSampleData();
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS sessions ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "sessionTime VARCHAR NOT NULL,"
                    + "mood VARCHAR NOT NULL,"
                    + "localTime VARCHAR NOT NULL,"
                    + "status VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSession(Session session) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO sessions (sessionTime, mood, localTime, status, id) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, session.getSessionTime());
            statement.setString(2, session.getMood());
            statement.setString(3, session.getLocalTime());
            statement.setString(4, session.getStatus());
            statement.setInt(5, session.getID());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSession(Session session) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE contacts SET sessionTime = ?, mood = ?, localTime = ?, status = ? WHERE id = ?");
            statement.setString(1, session.getSessionTime());
            statement.setString(2, session.getMood());
            statement.setString(3, session.getLocalTime());
            statement.setString(4, session.getStatus());
            statement.setInt(5, session.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSession(Session session) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM sessions WHERE id = ?");
            statement.setInt(1, session.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Session getSession(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sessions WHERE id = ?");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String sessionTime = resultSet.getString("sessionTime");
                String mood = resultSet.getString("mood");
                String localTime = resultSet.getString("localTime");
                String status = resultSet.getString("status");

                Session session = new Session(sessionTime, mood, localTime, status, id);
                return session;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Session> getAllSessions() {
        List<Session> sessions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM sessions";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String sessionTime = resultSet.getString("sessionTime");
                String mood = resultSet.getString("mood");
                String localTime = resultSet.getString("localTime");
                String status = resultSet.getString("status");

                Session session = new Session(sessionTime, mood, localTime, status, id);
                session.setID(id);
                sessions.add(session);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessions;
    }

    @Override
    public int getNumberOfMood(int id, String mood) {
        int total = 0;
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:MoodTrackr/src/main/resources/Database/sessions.db");
            PreparedStatement sta = con.prepareStatement("SELECT * FROM sessions WHERE id = ? AND mood = ?");
            sta.setInt(1, id); // Set the ID parameter
            sta.setString(2, mood);
            ResultSet rs = sta.executeQuery();
            while(rs.next()){
                total++;
            }
            rs.close();
            sta.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}