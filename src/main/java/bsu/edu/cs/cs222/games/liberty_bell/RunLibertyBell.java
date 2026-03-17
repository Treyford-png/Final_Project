package bsu.edu.cs.cs222.games.liberty_bell;

import bsu.edu.cs.cs222.characters.User;

public class RunLibertyBell {
    public static void main(String[] args) {
        User user = new User("Test", "test", 900);
        LibertyBellGame game = new LibertyBellGame(user);
        game.playGame();
    }
}