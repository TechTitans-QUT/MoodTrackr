import com.example.moodtrackr.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionTest {
    private Session session;
    @BeforeEach
    public void setUp() {
        session = new Session("1", "Happy", "12:00", "status", 1);
    }
    @Test
    public void testGetId() { assertEquals(1, session.getID()); }
    @Test
    public void testSetId() {
        session.setID(2);
        assertEquals(2, session.getID());
    }
    @Test
    public void testGetSessionTime() { assertEquals("1", session.getSessionTime()); }
    @Test
    public void testSetSessionTime() {
        session.setSessionTime("2");
        assertEquals("2", session.getSessionTime());
    }
    @Test
    public void testGetMood() { assertEquals("Happy", session.getMood()); }
    @Test
    public void testSetMood() {
        session.setMood("Sad");
        assertEquals("Sad", session.getMood());
    }
    @Test
    public void testGetLocalTime() { assertEquals("12:00", session.getLocalTime()); }
    @Test
    public void testSetLocalTime() {
        session.setLocalTime("1:00");
        assertEquals("1:00", session.getLocalTime());
    }
    @Test
    public void testGetStatus() { assertEquals("status", session.getStatus()); }
    @Test
    public void testSetStatus() {
        session.setStatus("status1");
        assertEquals("status1", session.getStatus());
    }

}