package com.example.moodtrackr.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDAO implements IUserDAO {
    private static String currentUserName;
    private final Connection connection;
    public SqliteUserDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
//        insertSampleData();
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL"
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
    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (firstName, lastName, password, email) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
            // Set the id of the new contact
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, password = ?, email = ? WHERE id = ?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                User user = new User(firstName, lastName, password, email);
                user.setId(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                User user = new User(firstName, lastName, password, email);
                user.setId(id);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Boolean verifyUser(String firstName, String lastName, String password) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT count(1) FROM users WHERE firstName = '" + firstName +
                    "' AND lastName = '" + lastName + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt(1) == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int getUserId(String firstName, String lastName, String password) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE firstName = '" + firstName +
                    "' AND lastName = '" + lastName + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setCurrentUser(String username) {
        currentUserName = username;
    }

    @Override
    public String getCurrentUser() {
        return currentUserName;
    }


}
