package bsu.edu.cs.cs222.games;
import bsu.edu.cs.cs222.games.faro.*;
import org.junit.jupiter.api.Test;

public class FaroTest {
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
            assert(player.getWagerCard.equals("") && player.getWagerPoints() == 0);
            player.setWager("K", 10);
            assert(player.getWagerCard().equals("K") && player.getWagerPoints() == 10);

            faro.drawCards();
            faro.scoreGame();
            if (faro.getLosingCard().equals(player.getWagerCard())) {
                assert player.getPoints() == previousPoints - 10;
                assert faro.getDealer().getPoints() == previousDealerPoints + 10;
            }
            else if (faro.getWinningCard().equals(player.getWagerCard())) {
                assert player.getPoints == previousPoints + 10;
                assert faro.getDealer().getPoints() == previousDealerPoints - 10;
            }
            else {
                assert player.getPoints() == previousPoints;
                assert faro.getDealer().getPoints() == previousDealerPoints;
            }
        }
    }
}
