package com.example.moodtrackr.model;

import java.util.List;

/**
 * Interface for the User Data Access Object that handles
 * the CRUD operations for the User class with the database.
 */
public interface IUserDAO {
    /**
     * Adds a new user to the database.
     * @param user The user to add.
     */
    void addUser(User user);
    /**
     * Updates an existing user in the database.
     * @param user The user to update.
     */
    void updateUser(User user);
    /**
     * Deletes a user from the database.
     * @param user The user to delete.
     */
    void deleteUser(User user);
    /**
     * Retrieves a user from the database.
     * @param id The id of the user to retrieve.
     * @return The user with the given id, or null if not found.
     */
    User getUser(int id);
    /**
     * Retrieves all users from the database.
     * @return A list of all users in the database.
     */
    List<User> getAllUsers();
    /**
     * Check if the user exists in the database.
     * @return True if the user exists, False if not.
     */
    Boolean verifyUser(String firstName, String lastName, String password);
    /**
     * Get the user ID by their name and password.
     * @return ID of the passed user.
     */
    int getUserId(String firstName, String lastName, String password);
    /**
     * Hash the password
     * @return Hashed password.
     */
    String passwordHash(String password);
}