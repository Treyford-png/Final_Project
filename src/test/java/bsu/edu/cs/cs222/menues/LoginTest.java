package bsu.edu.cs.cs222.menues;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    public void testCorrect() {
        assert(LoginTerminal.login("holden", "pass123!"));
    }

    @Test
    public void testIncorrectUsername() {
        assert(!LoginTerminal.login("hold", "pass123!"));
    }

    @Test
    public void testIncorrectPassword() {
        assert(!LoginTerminal.login("holden", "pass321!"));
    }

    @Test
    public void testBlankUsername() {
        assert(!LoginTerminal.login(null, "pass123!"));
    }

    @Test
    public void testBlankPassword() {
        assert(!LoginTerminal.login("holden", null));
    }

    @Test
    public void testBlankEverything() {
        assert(!LoginTerminal.login(null, null));
    }
}
