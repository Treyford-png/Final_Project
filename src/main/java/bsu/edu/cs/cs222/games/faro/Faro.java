package bsu.edu.cs.cs222.games.faro;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.libraries.cards.*;
import java.util.HashMap;
import java.util.Map;

public class Faro {
    private final User user;
    private final FaroPlayer[] players;
    private final Map<String, Integer> casekeep;
    private CardDeck deck;
    private String winningCard;
    private String losingCard;

    public Faro(User user) {
        this.user = user;
        FaroPlayerUser userPlayer = new FaroPlayerUser(user.getUsername(), user.getPoints());
        FaroPlayerCPU player2 = new FaroPlayerCPU("Angel Eyes", 5000);
        FaroPlayerCPU player3 = new FaroPlayerCPU("Tuco", 5000);
        player3.setDealer(true);
        players = new FaroPlayer[]{userPlayer, player2, player3};
        casekeep = new HashMap<>();
        deck = new CardDeck();
    }

    public void runGame() {
        startGame();
        int[] startPoints = new int[]{0, 0, 0};

        // Run 24 rounds ((52 - burn - final 3) / 2)
        for (int i = 0; i < 24; i++) {
            System.out.println("\n" + getPlayers() + "\n");
            System.out.println(casekeepOutput() + "\n");
            for (FaroPlayer player : players) {
                if (!player.getIsDealer()) {
                    System.out.println(player.getName() + "\n" + player.placeWager(deck) + "\n");
                }
            }

            for (int j = 0; j < 3; j++) {
                startPoints[j] = players[j].getPoints();
            }

            drawCards();
            System.out.println();
            scoreGame();

            for (int j = 0; j < 2; j++) {
                if (players[j].getPoints() >= startPoints[j]) {
                    System.out.println(players[j].getName() + " won " + (players[j].getPoints() - startPoints[j]));
                }
                else {
                    System.out.println(players[j].getName() + " lost " +
                            (((startPoints[j]) - players[j].getPoints()) / 2)); // Removes wager being readded
                }
            } // close for
        } // close for (i < 24)
    }

    public String getPlayers() {
        StringBuilder string = new StringBuilder();
        for (FaroPlayer player : players) {
            if (player.getIsDealer()) {
                string.append("(D) ");
            }
            string.append(player.getName()).append(" ").append(player.getPoints()).append("\n");
        }
        return string.toString();
    }

    public void startGame() {
        populateCasekeep();
        deck = new CardDeck();
        deck.shuffle();
        burn();
        winningCard = "";
        losingCard = "";
    }

    public void burn() {
        Card card = deck.deal();
        updateCasekeep(GetCardKey.getCardKey(card));
        System.out.println("Burned card: " + card.getShortName());
    }

    public void drawCards() {
        Card lCard = deck.deal();
        Card wCard = deck.deal();

        System.out.println("Dealer's card: [" + lCard.getShortName() + "]");
        System.out.println("Player's card: [" + wCard.getShortName() + "]");

        winningCard = GetCardKey.getCardKey(wCard);
        losingCard = GetCardKey.getCardKey(lCard);
        updateCasekeep(winningCard);
        updateCasekeep(losingCard);
    }

    public void populateCasekeep() {
        casekeep.put("2", 4);
        casekeep.put("3", 4);
        casekeep.put("4", 4);
        casekeep.put("5", 4);
        casekeep.put("6", 4);
        casekeep.put("7", 4);
        casekeep.put("8", 4);
        casekeep.put("9", 4);
        casekeep.put("10", 4);
        casekeep.put("k", 4);
        casekeep.put("q", 4);
        casekeep.put("j", 4);
        casekeep.put("a", 4);
    }

    public void updateCasekeep(String key) {
        int numLeft = casekeep.get(key) - 1;
        casekeep.put(key, numLeft);
    }

    public String casekeepOutput() {
        StringBuilder output = new StringBuilder();
        output.append("Casekeep:\n");
        for (String key : casekeep.keySet()) {
            output.append("[").append(key).append("]").append(" - ").append(casekeep.get(key)).append("\n");
        }
        return output.toString();
    }

    public void scoreGame() {
        FaroPlayer dealer = getDealer();
        for (FaroPlayer player : players) {
            if (!player.getIsDealer() ) {
                scorePlayer(player);
            }
        }
    }

    public void scorePlayer(FaroPlayer player) {
        FaroPlayer dealer = getDealer();

        // Subtracts winnings from losing cards
        int losingWager = player.getWagerPoints(losingCard);
        if (losingWager > 0) {
            dealer.addPoints(losingWager);
            player.clearWager(losingCard);
        }

        // Adds winnings to winning card
        int winningWager = player.getWagerPoints(winningCard);
        if (winningWager > 0) {
            dealer.subtractPoints(winningWager);
            player.addPoints(winningWager * 2); // Hands back wager and pays 1:1
            player.clearWager(winningCard);
        }
    }

    public FaroPlayer getDealer() {
        for (FaroPlayer player : players) {
            if (player.getIsDealer()) {
                return player;
            }
        }
        return null;
    }

    public void setDealer(FaroPlayer newDealer) {
        for (FaroPlayer player : players) {
            player.setDealer(false);
        }
        newDealer.setDealer(true);
    }

    public int getTypeLeft(String key) {
        return (casekeep.get(key));
    }

    public String getWinningCard() {
        return winningCard;
    }

    public String getLosingCard() {
        return losingCard;
    }
}