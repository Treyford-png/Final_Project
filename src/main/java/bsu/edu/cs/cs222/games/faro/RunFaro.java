package bsu.edu.cs.cs222.games.faro;

import bsu.edu.cs.cs222.characters.User;

public class RunFaro {
    static void main(String[] args) {
        Faro faro = new Faro(new User("User", "password", 1000));
        faro.runGame();
    }
}