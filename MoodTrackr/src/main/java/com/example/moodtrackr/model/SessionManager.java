package com.example.moodtrackr.model;

import java.util.List;

/**
 * A class that contains necessary methods to search, add, edit and delete sessions
 *
 */
public class SessionManager {
    private ISessionDAO sessionDAO;
    public SessionManager(ISessionDAO sessionDAO) { this.sessionDAO = sessionDAO; }

    /**
     * Method to search a session from a string query
     * @param query, a string to search in the database
     * @return  the matched strings from the query
     */
    public List<Session> searchSessions(String query) {
        return sessionDAO.getAllSessions()
                .stream()
                .filter(session -> isSessionMatched(session, query))
                .toList();
    }

    /**
     * Method to check if the given query matches the given session
     * @param session, a session from the database
     * @param query, the string to match with the session
     * @return returns true if matched, else false
     */
    private boolean isSessionMatched(Session session, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = session.getMood()
                + " " + session.getSessionTime()
                + " " + session.getLocalTime();
        return searchString.toLowerCase().contains(query);
    }

    public void addSession(Session session) { sessionDAO.addSession(session); }
    public void deleteSession(Session session) { sessionDAO.deleteSession(session); }
    public void updateSession(Session session) { sessionDAO.updateSession(session); }

    public List<Session> getAllSessions() { return sessionDAO.getAllSessions(); }

}