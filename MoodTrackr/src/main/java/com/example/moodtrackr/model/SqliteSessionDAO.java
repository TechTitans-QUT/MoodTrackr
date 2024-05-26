package com.example.moodtrackr.model;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public HashMap<String, Number> getUserIdSessions(int userID) throws SQLException {
        HashMap<String, Number> moodMap = new HashMap<String, Number>();
        HashMap<String, List<Double>> tempMoodMap = new HashMap<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:MoodTrackr/src/main/resources/Database/sessions.db");
            PreparedStatement sta = con.prepareStatement("SELECT * FROM sessions WHERE userID = ?");
            sta.setInt(1, userID); // Set the ID parameter
            ResultSet rs = sta.executeQuery();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            while(rs.next()){
                // Store mood and localTime
                String complexTime = rs.getString("localTime"); // Assuming column name is localTime
                String mood = rs.getString("mood"); // Assuming column name is mood

                Date date = (Date) sdf.parse(complexTime.split(" ")[0]);
                String dateKey = sdf.format(date);
                double moodValue = assignMood(mood);

                // Check to see if the date hasn't already been added
                tempMoodMap.computeIfAbsent(dateKey, k -> new ArrayList<>()).add(moodValue);
            }
            // Take the list of moods for each date and average them
            for (Map.Entry<String, List<Double>> entry : tempMoodMap.entrySet()) {
                List<Double> moods = entry.getValue();
                double sum = 0;
                for (double moodValue : moods) {
                    sum += moodValue;
                }
                double averageMood = sum / moods.size();
                moodMap.put(entry.getKey(), averageMood);
            }
            sta.close();
            rs.close();
            con.close();
        }
        catch (Exception d) {
            d.printStackTrace();
        }
        return moodMap;
    }
    public int assignMood(String mood) {
        return switch (mood) {
            case "Very Happy" -> 7;
            case "Happy" -> 6;
            case "Slightly Happy" -> 5;
            case "Slightly Sad" -> 3;
            case "Sad" -> 2;
            case "Very Sad" -> 1;
            default -> 4;
        };
    }
}