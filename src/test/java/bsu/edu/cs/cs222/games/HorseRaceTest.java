package bsu.edu.cs.cs222.games;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.horse_race.HorseRace;
import bsu.edu.cs.cs222.menues.SignUp;

import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class HorseRaceTest {
    @Test
    public void userPointsWon() {
        SignUp.signUp("HorseTest", "StrongPass123!", "StrongPass123!");
        User user = new User(Paths.get("src/main/resources/users/HorseTest.csv"));

        assert user.getPoints() == 5000;
        HorseRace horseRace = new HorseRace(user);
        user.makeWager(100);
        horseRace.calcPoints(0, 0, 100);
        assert user.getPoints() == 5500;

        File file = new File("src/main/resources/users/HorseTest.csv");
        file.delete();
    }

    @Test
    public void userPointsLost() {
        SignUp.signUp("HorseTest", "StrongPass123!", "StrongPass123!");
        User user = new User(Paths.get("src/main/resources/users/HorseTest.csv"));

        assert user.getPoints() == 5000;
        HorseRace horseRace = new HorseRace(user);
        user.makeWager(100);
        horseRace.calcPoints(1, 0, 100);
        assert user.getPoints() == 4900;

        File file = new File("src/main/resources/users/HorseTest.csv");
        file.delete();
    }

    @Test
    public void lostZeroPoints() {
        SignUp.signUp("HorseTest", "StrongPass123!", "StrongPass123!");
        User user = new User(Paths.get("src/main/resources/users/HorseTest.csv"));

        assert user.getPoints() == 5000;
        HorseRace horseRace = new HorseRace(user);
        user.makeWager(0);
        horseRace.calcPoints(1, 0, 0);
        assert user.getPoints() == 5000;

        File file = new File("src/main/resources/users/HorseTest.csv");
        file.delete();
    }

    @Test
    public void wonZeroPoints() {
        SignUp.signUp("HorseTest", "StrongPass123!", "StrongPass123!");
        User user = new User(Paths.get("src/main/resources/users/HorseTest.csv"));

        assert user.getPoints() == 5000;
        HorseRace horseRace = new HorseRace(user);
        user.makeWager(0);
        horseRace.calcPoints(0, 0, 0);
        assert user.getPoints() == 5000;

        File file = new File("src/main/resources/users/HorseTest.csv");
        file.delete();

    }
}
