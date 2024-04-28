package com.example.moodtrackr.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteSessionDAO implements ISessionDAO {
    private final Connection connection;
    public SqliteSessionDAO() {
        connection = SqliteConnection.getInstance();
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

//    private void insertSampleData() {
//        try {
//            // Clear before inserting
//            Statement clearStatement = connection.createStatement();
//            String clearQuery = "DELETE FROM users";
//            clearStatement.execute(clearQuery);
//            Statement insertStatement = connection.createStatement();
//            String insertQuery = "INSERT INTO users (firstName, lastName, password, email) VALUES "
//                    + "('John', 'Doe', 'pass1', 'johndoe@example.com'),"
//                    + "('Jane', 'Doe', 'pass2', 'janedoe@example.com'),"
//                    + "('Jay', 'Doe', 'pass3', 'jaydoe@example.com')";
//            insertStatement.execute(insertQuery);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void addSession(Session session) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO sessions (sessionTime, mood, localTime, status) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, session.getSessionTime());
            statement.setString(2, session.getMood());
            statement.setString(3, session.getLocalTime());
            statement.setInt(4, session.getStatus());
            statement.executeUpdate();

            // set id for new session
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
            PreparedStatement statement = connection.prepareStatement("UPDATE contacts SET sessionTime = ?, mood = ?, localTime = ?, status = ? WHERE id = ?");
            statement.setString(1, session.getSessionTime());
            statement.setString(2, session.getMood());
            statement.setString(3, session.getLocalTime());
            statement.setInt(4, session.getStatus());
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
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String sessionTime = resultSet.getString("sessionTime");
                String mood = resultSet.getString("mood");
                String localTime = resultSet.getString("localTime");
                int status = resultSet.getInt("status");
                Session session = new Session(sessionTime, mood, localTime, status);
                session.setID(id);
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
                int status = resultSet.getInt("status");

                Session session = new Session(sessionTime, mood, localTime, status);
                session.setID(id);
                sessions.add(session);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessions;
    }
}