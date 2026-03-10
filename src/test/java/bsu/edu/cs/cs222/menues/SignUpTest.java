package bsu.edu.cs.cs222.menues;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class SignUpTest {
    @Test
    public void testNewUsernamePassword() {
        File file = new File("src/main/resources/users/SignUpTest.csv");
        file.delete();

        assert(SignUp.signUp("SignUpTest", "1234").equals("Account created"));
        assert(LogIn.login("SignUpTest", "1234"));
        file = new File("src/main/resources/users/SignUpTest.csv");
        file.delete();
    }

    @Test
    public void testExistingUsername() throws IOException {
        File file = new File("src/main/resources/users/SignUpTest.csv");
        file.createNewFile();

        assert(!SignUp.signUp("SignUpTest", "1234").equals("Account with that username already exists"));
        file.delete();
    }

    @Test
    public void testMatchingPasswords() {
        assert(SignUp.validatePassword("1234", "1234"));
    }

    @Test
    public void testNonMatchingPasswords() {
        assert(SignUp.validatePassword("1234", "4321"));
    }
}
