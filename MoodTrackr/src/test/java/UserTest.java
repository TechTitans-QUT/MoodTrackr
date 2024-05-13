import com.example.moodtrackr.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;
    @BeforeEach
    public void setUp() {
        user = new User("John", "Doe", "john.doe@example.com", "password1");
    }
    @Test
    public void testGetId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }
    @Test
    public void testGetFirstName() {
        assertEquals("John", user.getFirstName());
    }
    @Test
    public void testSetFirstName() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }
    @Test
    public void testGetLastName() {
        assertEquals("Doe", user.getLastName());
    }
    @Test
    public void testSetLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }
    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", user.getEmail());
    }
    @Test
    public void testSetEmail() {
        user.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", user.getEmail());
    }
    @Test
    public void testGetPhone() {
        assertEquals("password1", user.getPassword());
    }
    @Test
    public void testSetPhone() {
        user.setPassword("password11");
        assertEquals("password11", user.getPassword());
    }
    @Test
    public void testGetFullName() {
        assertEquals("John Doe", user.getFullName());
    }
}