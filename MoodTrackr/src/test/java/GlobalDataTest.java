import com.example.moodtrackr.GlobalData;
import com.example.moodtrackr.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalDataTest {
    private final User user = new User("John", "Doe", "john.doe@example.com", "password1");
    @BeforeEach
    public void setUp() {
        GlobalData.getInstance().setYourObject(user);
    }
    @Test
    public void testGetYourObject() {
        assertEquals(user, GlobalData.getInstance().getYourObject());
    }
}
