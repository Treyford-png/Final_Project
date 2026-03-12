package bsu.edu.cs.cs222.games;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.vingt_un.Hand;
import bsu.edu.cs.cs222.games.vingt_un.VingtUn;
import bsu.edu.cs.cs222.libraries.cards.Card;

public class VingtUnTest {
    public void main() {
        VingtUn game = new VingtUn(new User("Test", "test", 500));
        game.startGame();

        game.userTurn();

        game.aiTurn(game.getPlayer2());
        Hand hand = game.getPlayer2().getHand();
        for (Card card : hand.getHand()) {
            System.out.print(card.getShortName());
        }
        System.out.println(game.getPlayer2().getHand().getHandValue());

        game.aiTurn(game.getPlayer3());
        hand = game.getPlayer3().getHand();
        for (Card card : hand.getHand()) {
            System.out.print(card.getShortName());
        }
        System.out.println(game.getPlayer3().getHand().getHandValue());
    }
}
