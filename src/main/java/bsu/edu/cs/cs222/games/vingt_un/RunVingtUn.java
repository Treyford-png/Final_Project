package bsu.edu.cs.cs222.games.vingt_un;

import bsu.edu.cs.cs222.characters.User;

/**
 * Runs an independent instance of VingtUn in terminal
 * Used for testing
 *
 * @author Holden Hankins
 */
public class RunVingtUn {
    static void main() {
        VingtUn game = new VingtUn(new User("You", "test", 5000));
        game.runGame();
    }
}