package bsu.edu.cs.cs222.games;

import bsu.edu.cs.cs222.games.vingt_un.VingtUn;
import bsu.edu.cs.cs222.characters.User;
import org.junit.jupiter.api.Test;


public class VingtUnTest {

    @Test
    public void testAiTurn() {
        VingtUn game = new VingtUn(new User("test", "test", 0));
        for (int i = 0; i < 1000; i++) {
            game.startGame();
            game.aiTurn(game.getPlayer2());
            assert(game.getPlayer2().getHand().getHandValue() < 22);
            game.clearHands();
            game.shuffleDeck();
        }
    }
}
