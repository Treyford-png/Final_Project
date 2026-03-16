package bsu.edu.cs.cs222.games;
import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.faro.*;
import bsu.edu.cs.cs222.libraries.cards.*;
import org.junit.jupiter.api.Test;

public class FaroTest {
    /*
    @Test
    public void testPlayGame() {
        Faro faro = new Faro(null);
        FaroPlayer player = new FaroPlayer("Test", 1000);
        faro.burn();
        int previousPoints;
        int previousDealerPoints;
        for (int i = 0; i < 24; i++) {
            previousPoints = player.getPoints();
            previousDealerPoints = faro.getDealer().getPoints();
            player.clearWager();
            assert(player.getWagerCard().isEmpty() && player.getWagerPoints() == 0);
            player.setWager("K", 10);
            assert(player.getWagerCard().equals("K") && player.getWagerPoints() == 10);

            faro.drawCards();
            faro.scoreGame();
            if (faro.getLosingCard().equals(player.getWagerCard())) {
                assert player.getPoints() == previousPoints - 10;
                assert faro.getDealer().getPoints() == previousDealerPoints + 10;
            }
            else if (faro.getWinningCard().equals(player.getWagerCard())) {
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
        Faro faro = new Faro(new User("Test", "test", 500));
        faro.populateCasekeep();
        CardDeck deck = new CardDeck(); // unshuffled 52 card deck
        Card card = deck.lookAtTop(); // 2h
        faro.updateCasekeep(faro.getCardKey(card));
        assert(faro.getTypeLeft("2") == 3);
    }

     */
}
