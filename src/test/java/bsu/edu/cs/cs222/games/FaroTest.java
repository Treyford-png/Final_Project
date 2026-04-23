package bsu.edu.cs.cs222.games;
import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.faro.*;
import bsu.edu.cs.cs222.libraries.cards.*;
import org.junit.jupiter.api.Test;

public class FaroTest {
    @Test
    public void testPlayGame() {
        Faro faro = new Faro(new User("Test", "test", 1000));
        FaroPlayer player = new FaroPlayer("Test", 1000);
        faro.populateCasekeep();
        faro.burn();
        int previousPoints;
        int previousDealerPoints;
        for (int i = 0; i < 24; i++) {
            System.out.println(i);
            previousPoints = player.getPoints();
            previousDealerPoints = faro.getDealer().getPoints();

            faro.drawCards();
            faro.scoreGame();
            if (player.getWagerPoints(faro.getLosingCard()) > 0) {
                assert player.getPoints() == previousPoints - 10;
                assert faro.getDealer().getPoints() == previousDealerPoints + 10;
            }
            else if (player.getWagerPoints(faro.getWinningCard()) > 0) {
                assert player.getPoints() == previousPoints + 10;
                assert faro.getDealer().getPoints() == previousDealerPoints - 10;
            }
            else {
                assert player.getPoints() == previousPoints;
                assert faro.getDealer().getPoints() == previousDealerPoints;
            }
        }
    }

    @Test
    public void testUpdateCasekeep() {
        FaroController faro = new FaroController(new User("Test", "test", 500));
        faro.getCasekeep().populate();
        CardDeck deck = new CardDeck(); // unshuffled 52 card deck
        Card card = deck.lookAtTop(); // 2h
        faro.getCasekeep().update(GetCardKey.getCardKey(card));
        assert(faro.getTypeLeft("2") == 3);
    }
}
