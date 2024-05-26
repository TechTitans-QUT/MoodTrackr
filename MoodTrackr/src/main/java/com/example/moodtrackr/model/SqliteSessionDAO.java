package com.example.moodtrackr.model;

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
                    + "userID INTEGER,"
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO sessions (sessionTime, mood, localTime, status, userID) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, session.getSessionTime());
            statement.setString(2, session.getMood());
            statement.setString(3, session.getLocalTime());
            statement.setString(4, session.getStatus());
            statement.setInt(5, session.getUserID());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                session.setID(generatedKeys.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSession(Session session) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE sessions SET sessionTime = ?, mood = ?, localTime = ?, status = ?, userID = ? WHERE id = ?");
            statement.setString(1, session.getSessionTime());
            statement.setString(2, session.getMood());
            statement.setString(3, session.getLocalTime());
            statement.setString(4, session.getStatus());
            statement.setInt(5, session.getUserID());
            statement.setInt(6, session.getID());

            System.out.println(session.getStatus() + session.getUserID() + session.getID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Session updated successfully.");
            } else {
                System.out.println("No session updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle specific SQL exceptions (e.g., SQLException, DataAccessException) here
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions here
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
                int userID = resultSet.getInt("userID");

                Session session = new Session(sessionTime, mood, localTime, status, userID);
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
                int userID = resultSet.getInt("userID");
                String sessionTime = resultSet.getString("sessionTime");
                String mood = resultSet.getString("mood");
                String localTime = resultSet.getString("localTime");
                String status = resultSet.getString("status");

                Session session = new Session(sessionTime, mood, localTime, status, userID);
                session.setID(id);
                sessions.add(session);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessions;
    }

    @Override
    public int getNumberOfMood(int userID, String mood) {
        int total = 0;
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:MoodTrackr/src/main/resources/Database/sessions.db");
            PreparedStatement sta = con.prepareStatement("SELECT * FROM sessions WHERE userID = ? AND mood = ?");
            sta.setInt(1, userID); // Set the ID parameter
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