package bsu.edu.cs.cs222.games;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.roulette.Roulette;
import org.junit.jupiter.api.Test;

public class RouletteTest {
    @Test
    public void testPopulateWheel() {
        User user = new User("Test", "test", 1000);
        Roulette roulette = new Roulette(user);
        roulette.populateRouletteWheel();
        assert roulette.getColor("1").equals("red");
        assert roulette.getColor("8").equals("black");
        assert roulette.getColor("0").equals("green");
        assert roulette.getColor("00").equals("green");
    }

    @Test
    public void testSpinWheel() {
        User user = new User("Test", "test", 1000);
        Roulette roulette = new Roulette(user);
        for (int i = 0; i < 1000; i++) {
            roulette.spinWheel();
        }
    }

    @Test
    public void testNumberWin() {
        User user = new User("Test", "test", 1000);
        Roulette roulette = new Roulette(user);
        assert roulette.calcResults("1", "1", "red", 100) == 1700;
    }

    @Test
    public void testColorWin() {
        User user = new User("Test", "test", 1000);
        Roulette roulette = new Roulette(user);
        assert roulette.calcResults("1", "x", "red", 100) == 1200;
    }

    @Test
    public void testColorLoss() {
        User user = new User("Test", "test", 1000);
        Roulette roulette = new Roulette(user);
        assert roulette.calcResults("1", "x", "black", 100) == 900;
    }

    @Test
    public void testNumberLoss() {
        User user = new User("Test", "test", 1000);
        Roulette roulette = new Roulette(user);
        assert roulette.calcResults("1", "14", "red", 100) == 900;
    }

    @Test
    public void testInvalidNumber() {
        User user = new User("Test", "test", 1000);
        Roulette roulette = new Roulette(user);
        assert roulette.calcResults("1", "29", "red", 100) == 900;
    }

    @Test
    public void testNoWager() {
        User user = new User("Test", "test", 1000);
        Roulette roulette = new Roulette(user);
        assert roulette.calcResults("1", "", "", 0) == 1000;
    }
}
