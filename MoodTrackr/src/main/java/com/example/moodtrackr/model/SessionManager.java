package com.example.moodtrackr.model;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private ISessionDAO sessionDAO;
    public SessionManager(ISessionDAO sessionDAO) { this.sessionDAO = sessionDAO; }

    public List<Session> searchSessions(String query) {
        return sessionDAO.getAllSessions()
                .stream()
                .filter(session -> isSessionMatched(session, query))
                .toList();
    }

    private boolean isSessionMatched(Session session, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = session.getMood()
                + " " + session.getTime()
                + " " + session.getLocalTime();
        return searchString.toLowerCase().contains(query);
    }

    public void addSession(Session session) { sessionDAO.addSession(session); }
    public void deleteSession(Session session) { sessionDAO.deleteSession(session); }
    public void updateSession(Session session) { sessionDAO.updateSession(session); }

    public List<Session> getAllSessions() { return sessionDAO.getAllSessions(); }

}