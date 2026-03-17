package bsu.edu.cs.cs222.games.roulette;
import bsu.edu.cs.cs222.characters.User;

public class RunRoulette {
    public static void main(String[] args) {
        Roulette roulette = new Roulette(new User("Test", "test", 1000));
        roulette.runRoulette();

    }
}
