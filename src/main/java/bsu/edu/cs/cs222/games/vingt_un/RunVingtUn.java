package bsu.edu.cs.cs222.games.vingt_un;

import bsu.edu.cs.cs222.characters.User;

public class RunVingtUn {
    public static void main() {
        VingtUn game = new VingtUn(new User("You", "test", 500));
        game.runGame();
    }
}