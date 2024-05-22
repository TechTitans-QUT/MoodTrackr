package com.example.moodtrackr.model;

import com.example.moodtrackr.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * A public class that contains mock data for the listview (NOT BEING USED)
 */
public class MockSessionDAO implements ISessionDAO {
    /**
     * A static list of contacts to be used as a mock database.
     */
    public final ArrayList<Session> sessions = new ArrayList<>();
    private int autoIncrementedId = 0;

    public MockSessionDAO() {
        // Add some initial contacts to the mock database
//        addSession(new Session("03:00:23", "Happy", "localTime1", "0"));
//        addSession(new Session("01:04:52", "Very Happy", "localTime2", "0"));
//        addSession(new Session("06:10:26", "Sad", "localTime3", "0"));

    }
    @Override
    public void addSession(Session session) {
        session.setID(autoIncrementedId);
        autoIncrementedId++;
        sessions.add(session);
    }

    @Override
    public void updateSession(Session session) {
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getID() == session.getID()) {
                sessions.set(i, session);
                break;
            }
        }
    }

    @Override
    public void deleteSession(Session session) {
        sessions.remove(session);
    }

    @Override
    public Session getSession(int id) {
        for (Session session : sessions) {
            if (session.getID() == id) {
                return session;
            }
        }
        return null;
    }

    @Override
    public List<Session> getAllSessions() {
        return new ArrayList<>(sessions);
    }
}