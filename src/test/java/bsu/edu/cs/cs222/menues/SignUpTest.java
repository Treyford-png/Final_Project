package bsu.edu.cs.cs222.menues;
import bsu.edu.cs.cs222.menues.log_in.LogIn;
import bsu.edu.cs.cs222.menues.log_in.SignUp;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SignUpTest {
    @Test
    public void testNewUsernamePassword() {
        Path file = Paths.get("src/main/resources/users/SignUpTest.csv");
        try {
            Files.delete(file);
        } catch (IOException e) {

        }

        assert(SignUp.signUp("SignUpTest", "Test1234!", "Test1234!").equals("Account Created"));
        assert(LogIn.login("SignUpTest", "Test1234!")!= null);
        try {
            Files.delete(file);
        } catch (IOException e) {

        }
    }

    @Test
    public void testExistingUsername() throws IOException {
        File file = new File("src/main/resources/users/SignUpTest.csv");
        file.createNewFile();

        assert(SignUp.signUp("SignUpTest", "Tets1234", "Test1234").equals("Account with that username already exists"));
        file.delete();
    }

    @Test
    public void testNonMatchingPasswords() {
        assert(SignUp.validatePassword("NoMatch1234!", "DoesNotMatch4321!").equals("Passwords do not match"));
    }

    @Test
    public void testWeakPasswords() {
        assert(SignUp.isStrongPassword("Strong1234!"));
        assert(!SignUp.isStrongPassword("no_uppercase_1234!"));
        assert(!SignUp.isStrongPassword("no_lowercase_1234!"));
        assert(!SignUp.isStrongPassword("NoNumbers!"));
        assert(!SignUp.isStrongPassword("NoSymbols1234"));
    }
}
