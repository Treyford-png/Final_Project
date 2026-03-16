package bsu.edu.cs.cs222.characters;
import bsu.edu.cs.cs222.menues.SignUp;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

public class UserTest {
    @Test
    public void testAddPoints() {
        User user = new User("Test", "test", 0);
        user.addPoints(500);
        assert user.getPoints() == 500;
    }

    @Test
    public void testWager() {
        User user = new User("Test", "test", 500);
        boolean madeWager = user.makeWager(200);
        assert madeWager;
        assert user.getPoints() == 300;

    }

    @Test
    public void testAllIn() {
        User user = new User("Test", "test", 300);
        boolean madeWager = user.makeWager(500);
        assert !madeWager;
        int allIn = user.allIn();
        assert allIn == 300;
    }

    @Test
    public void testReadUserFile() {
        SignUp.signUp("Test", "StrongPass123!", "StrongPass123!");
        User user = new User(Paths.get("src/main/resources/users/Test.csv"));
        assert user.getUsername().equals("Test");
        assert user.getPoints() == 0;
        File file = new File("src/main/resources/users/Test.csv");
        file.delete();

    }

    @Test
    public void testSavePoints() {
        SignUp.signUp("Test", "StrongPass123!", "StrongPass123!");
        User user = new User(Paths.get("src/main/resources/users/Test.csv"));
        assert user.getUsername().equals("Test");
        assert user.getPoints() == 0;
        user.addPoints(500);
        user.savePoints();

        User userChecker = new User(Paths.get("src/main/resources/users/Test.csv"));
        assert (userChecker.getPoints() == 500);
        File file = new File("src/main/resources/users/Test.csv");
        file.delete();
    }

}
