package bsu.edu.cs.cs222.menues;
import bsu.edu.cs.cs222.menues.log_in.LogIn;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    public void testCorrect() {
        assert(LogIn.login("holden", "pass123!") != null);
    }

    @Test
    public void testIncorrectUsername() {
        assert(LogIn.login("hold", "pass123!") == null);
    }

    @Test
    public void testIncorrectPassword() {
        assert(LogIn.login("holden", "pass321!") == null);
    }

    @Test
    public void testBlankUsername() {
        assert(LogIn.login(null, "pass123!") == null);
    }

    @Test
    public void testBlankPassword() {
        assert(LogIn.login("holden", null) == null);
    }

    @Test
    public void testBlankEverything() {
        assert(LogIn.login(null, null) == null);
    }
}
