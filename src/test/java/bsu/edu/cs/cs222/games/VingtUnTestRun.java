package bsu.edu.cs.cs222.games;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.vingt_un.Hand;
import bsu.edu.cs.cs222.games.vingt_un.VingtUn;
import bsu.edu.cs.cs222.libraries.cards.Card;

public class VingtUnTestRun {
    /**
     * Working alongside assert based tests, a non-hard coded test to see real output seems best
     */
    public void main() {
        VingtUn game = new VingtUn(new User("Test", "test", 500));
        game.startGame();

        game.aiTurn(game.getPlayer2());
        System.out.println(game.getPlayer2().getHand().getOutput());
        System.out.println();
        game.aiTurn(game.getPlayer3());
        System.out.println(game.getPlayer3().getHand().getOutput());
    }
}
