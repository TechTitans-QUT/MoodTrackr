package com.example.moodtrackr.model;

public class SessionManager {
    private ISessionDAO sessionDAO;
    public SessionManager(ISessionDAO sessionDAO) { this.sessionDAO = sessionDAO; }
    public void addSession(Session session) { sessionDAO.addSession(session); }
    public void deleteSession(Session session) { sessionDAO.deleteSession(session); }
    public void updateSession(Session session) { sessionDAO.updateSession(session); }

}