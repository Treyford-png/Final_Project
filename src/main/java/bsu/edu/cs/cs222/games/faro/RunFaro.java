package bsu.edu.cs.cs222.games.faro;

import bsu.edu.cs.cs222.characters.User;

/**
 * Allows an independent instance of Faro to be started
 * Used for debugging
 *
 * @author Holden Hankins
 */
public class RunFaro {
    static void main(String[] args) {
        FaroController faro = new FaroController(new User("User", "password", 1000));
        faro.runGame();
    }
}