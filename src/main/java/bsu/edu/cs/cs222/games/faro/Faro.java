package bsu.edu.cs.cs222.games.faro;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.libraries.cards.*;

import java.util.*;

public class Faro {
    private final User user;
    private final FaroPlayer[] players;
    private final Map<String, Integer> casekeep; // Used to hold list of cards left
    private CardDeck deck;
    private String winningCard;
    private String losingCard;

    public Faro(User user) {
        this.user = user;
        // Populate players[]
        FaroPlayerUser userPlayer = new FaroPlayerUser(user.getUsername(), user.getPoints());
        FaroPlayerCPU player2 = new FaroPlayerCPU("Angel Eyes", 5000);
        FaroPlayerCPU player3 = new FaroPlayerCPU("Tuco", 5000);
        player3.setDealer(true);
        players = new FaroPlayer[]{userPlayer, player2, player3};

        // Establish deck
        casekeep = new HashMap<>();
        deck = new CardDeck();
    }

    /**
     * Runs the game itself with all of its logic handled in other functions
     */
    public void runGame() {
        startGame();
        int[] startPoints = new int[]{0, 0, 0}; // Saves for end game calculations

        // Run 24 rounds ((52 - burn - final 3) / 2)
        for (int i = 0; i < 24; i++) {
            // Print casekeep
            System.out.println("\n" + getPlayers() + "\n");
            System.out.println(casekeepOutput() + "\n");

            // User turn
            for (FaroPlayer player : players) {
                if (!player.getIsDealer()) {
                    System.out.println(player.getName() + "\n" + player.placeWager(deck) + "\n");
                }
            }

            // Update starting points
            for (int j = 0; j < 3; j++) {
                startPoints[j] = players[j].getPoints();
            }

            // Draw 2 cards and print results
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
        user.addPoints(players[0].getPoints() - user.getPoints()); // Saves user points
        finalThree();
    }

    /**
     * Prints out the players with the amount of points they hold
     */
    public String getPlayers() {
        StringBuilder string = new StringBuilder();
        for (FaroPlayer player : players) {
            if (player.getIsDealer()) { // Denotes dealer
                string.append("(D) ");
            }
            string.append(player.getName()).append(" ").append(player.getPoints()).append("\n");
        }
        return string.toString();
    }

    /**
     * Resets the game state for a run
     */
    public void startGame() {
        populateCasekeep();
        deck = new CardDeck();
        deck.shuffle();
        burn();
        winningCard = "";
        losingCard = "";
    }

    // Removes 1 card from the deck
    public void burn() {
        Card card = deck.deal();
        updateCasekeep(GetCardKey.getCardKey(card));
        System.out.println("Burned card: " + card.getShortName());
    }

    /**
     * First drawn card is losing/dealer's card, second drawn card is winning/player's card
     */
    public void drawCards() {
        // Draws both cards
        Card lCard = deck.deal();
        Card wCard = deck.deal();

        // Prints cards for players
        System.out.println("Dealer's card: [" + lCard.getShortName() + "]");
        System.out.println("Player's card: [" + wCard.getShortName() + "]");

        // Updates casekeep
        winningCard = GetCardKey.getCardKey(wCard);
        losingCard = GetCardKey.getCardKey(lCard);
        updateCasekeep(winningCard);
        updateCasekeep(losingCard);
    }

    /**
     * Casekeep is used as a way to keep track of cards remaining in the deck
     */
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

    /**
     * Removed card from the casekeep
     * @param key of a card in the HashMap
     */
    public void updateCasekeep(String key) {
        int numLeft = casekeep.get(key) - 1;
        casekeep.put(key, numLeft);
    }

    /**
     * Prints entire casekeep
     * @return formated [key] - #\n...
     */
    public String casekeepOutput() {
        StringBuilder output = new StringBuilder();
        output.append("Casekeep:\n");
        for (String key : casekeep.keySet()) {
            output.append("[").append(key).append("]").append(" - ").append(casekeep.get(key)).append("\n");
        }
        return output.toString();
    }

    /**
     * Runs the scoring loop for each player
     * Dealer is excluded as they do not wager and make points from players losing
     */
    public void scoreGame() {
        FaroPlayer dealer = getDealer();
        for (FaroPlayer player : players) {
            if (!player.getIsDealer() ) {
                scorePlayer(player);
            }
        }
    }

    /**
     * Updates player scoring
     * @param player an NPC or User FaroPlayer
     */
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

    /**
     * Loops through list of players checking if they are the dealer
     * @return first (and hopefully only) player listed as IsDealer
     */
    public FaroPlayer getDealer() {
        for (FaroPlayer player : players) {
            if (player.getIsDealer()) {
                return player;
            }
        }
        return null;
    }

    /**
     * Clears the current dealer and assigns someone as the next one
     * @param newDealer player that will become dealer
     */
    public void setDealer(FaroPlayer newDealer) {
        for (FaroPlayer player : players) {
            player.setDealer(false);
        }
        newDealer.setDealer(true);
    }

    /**
     * If three cards are left, players guess on the order that they will appear in
     */
    public void finalThree() {
        char[] guessedOrder = new char[3];
        System.out.println("Three cards left:\n" + casekeepOutput() + "\n\nGuess the cards in order:");
        Scanner faroScanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.print("\n" + (i + 1) + ": ");
            guessedOrder[i] = faroScanner.nextLine().charAt(0);
        }
        int wager = players[0].getAmountToWager();
        user.makeWager(wager);
        char[] order = new char[3];
        for (int i = 0; i < 2; i++) {
            order[i] = GetCardKey.getCardKey(deck.deal()).charAt(0);
        }
        scoreFinalThree(guessedOrder, order, wager, players[0]);
        AIFinalThree(players[1], order);
        AIFinalThree(players[2], order);
    }

    public void scoreFinalThree(char[] guessedOrder, char[] order, int wager, FaroPlayer player) {
        if (Arrays.equals(guessedOrder, order)) {
            System.out.println(player.getName() + " won " + (wager * 5));
            players[0].addPoints(wager * 5);
        }
    }

    public void AIFinalThree(FaroPlayer player, char[] order) {
        if (player.getIsDealer()) {
            return;
        }

        char[] guess = order;
        Random rand = new Random();

        for (int i = guess.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            char temp = guess[i];
            guess[i] = guess[j];
            guess[j] = temp;
        }

        System.out.println("\n" + player.getName() + " guessed [" + guess[0] + "] ["
                + guess[1] + "] [" + guess[2] +"]");
        scoreFinalThree(guess, order, player.getAmountToWager(), player);
    }

    /**
     * Following 3 are used for debugging only
     */

    public String getWinningCard() {
        return winningCard;
    }

    public String getLosingCard() {
        return losingCard;
    }

    public int getTypeLeft(String key) {
        return casekeep.get(key);
    }
}