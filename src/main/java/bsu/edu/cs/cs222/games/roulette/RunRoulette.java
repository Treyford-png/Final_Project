package bsu.edu.cs.cs222.games.roulette;

import bsu.edu.cs.cs222.characters.User;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RunRoulette {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("src/main/resources/users/You.csv");
            Roulette roulette = new Roulette(new User(path));
            roulette.runRoulette();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
