package com.example.moodtrackr.model;

import java.util.List;

/**
 */
public interface ISessionDAO {
    /**
     * Adds a new session to the database.
     * @param session The session to add.
     */
    public void addSession(Session session);
    /**
     * Updates an existing session in the database.
     * @param session The session to update.
     */
    public void updateSession(Session session);
    /**
     * Deletes a session from the database.
     * @param session The session to delete.
     */
    public void deleteSession(Session session);
    /**
     * Retrieves a session from the database.
     * @param id The id of the session to retrieve.
     * @return The contact with the given id, or null if not found.
     */
    public Session getSession(int id);
    /**
     * Retrieves all session from the database.
     * @return A list of all session in the database.
     */
    public List<Session> getAllSessions();
}