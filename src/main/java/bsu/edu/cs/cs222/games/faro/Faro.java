package bsu.edu.cs.cs222.games.faro;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.libraries.cards.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class Faro {
    private User user;
    private FaroPlayer[] players;
    private final Casekeep casekeep; // Used to hold list of cards left
    private CardDeck deck;
    private String winningCard;
    private String losingCard;
    @FXML private Label dealerCard, playerCard, userPointsLabel,
            npcOneName, npcTwoName, npcOneTotal, npcTwoTotal, npcOneBetPoints, npcTwoBetPoints, playerBetPoints;
    @FXML private Button leftPlayerBets;

    public Faro() {
        casekeep = new Casekeep();
        deck = new CardDeck();
        deck.shuffle();
    }

    public Faro(User user) {
        this.user = user;
        // Populate players[]
        FaroPlayerUser userPlayer = new FaroPlayerUser(user.getUsername(), user.getPoints());
        FaroPlayerCPU player2 = new FaroPlayerCPU("Angel Eyes", 5000);
        FaroPlayerCPU player3 = new FaroPlayerCPU("Tuco", 5000);
        player3.setDealer(true);
        players = new FaroPlayer[]{userPlayer, player2, player3};

        // Establish deck
        casekeep = new Casekeep();
        deck = new CardDeck();
        deck.shuffle();
    }

    public void setUser(User user) {
        this.user = user;
        FaroPlayerUser userPlayer = new FaroPlayerUser(user.getUsername(), user.getPoints());
        FaroPlayerCPU player2 = new FaroPlayerCPU("Angel Eyes", 5000);
        npcOneName.setText(player2.getName());
        npcOneTotal.setText(String.valueOf(player2.getPoints()));

        FaroPlayerCPU player3 = new FaroPlayerCPU("Tuco", 5000);
        player3.setDealer(true);
        npcTwoName.setText(player3.getName());
        npcTwoTotal.setText(String.valueOf(player3.getPoints()));
        players = new FaroPlayer[]{userPlayer, player2, player3};
        updateUserPointsLabel();
    }

    public Casekeep getCasekeep() {
        return casekeep;
    }

    /**
     * Runs the game itself with all of its logic handled in other functions
     */
    public void runGame() {
        startGame();
        int[] startPoints = new int[]{0, 0, 0}; // Saves for end game calculations

        // Run 24 rounds ((52 - burn - final 3) / 2)
        for (int i = 0; i < 24; i++) {

            // User turn
            for (FaroPlayer player : players) {
                if (!player.getIsDealer()) {
                    player.placeWager(deck);
                }
            }

            // Update starting points
            for (int j = 0; j < 3; j++) {
                startPoints[j] = players[j].getPoints();
            }

            // Draw 2 cards and print results
            drawCards();
            scoreGame();
            for (int j = 0; j < 2; j++) {
                if (players[j].getPoints() >= startPoints[j]) {
                    JOptionPane.showMessageDialog (null,
                            players[j].getName() + " won " + (players[j].getPoints() - startPoints[j]));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            players[j].getName() + " lost " +
                                    (((startPoints[j]) - players[j].getPoints()) / 2)); // Removes wager being readded
                }
            } // close for
        } // close for (i < 24)
        user.addPoints(players[0].getPoints() - user.getPoints()); // Saves user points
        finalThree();
    }

    /**
     * Resets the game state for a run
     */
    public void startGame() {
        casekeep.populate();
        deck = new CardDeck();
        deck.shuffle();
        burn();
        winningCard = "";
        losingCard = "";
    }

    // Removes 1 card from the deck
    public void burn() {
        Card card = deck.deal();
        casekeep.update(GetCardKey.getCardKey(card));
        JOptionPane.showMessageDialog(null, ("Burned card: " + card.getShortName()));
    }

    /**
     * First drawn card is losing/dealer's card, second drawn card is winning/player's card
     */
    public void drawCards() {
        playerWagers();

        // Draws both cards
        Card lCard = deck.deal();
        Card wCard = deck.deal();

        // Prints cards for players
        dealerCard.setText(lCard.getCardOutput());
        playerCard.setText(wCard.getCardOutput());

        // Updates casekeep
        winningCard = GetCardKey.getCardKey(wCard);
        losingCard = GetCardKey.getCardKey(lCard);
        casekeep.update(winningCard);
        casekeep.update(losingCard);

        scoreGame();
    }

    public void playerWagers() {
        players[1].placeWager(deck);
        players[2].placeWager(deck);

        // updates labels
        npcOneBetPoints.setText("(D)");
        npcTwoBetPoints.setText("(" + players[2].getAmountPlaced() + "p)");
        playerBetPoints.setText("(" + players[0].getAmountPlaced() + "p)");
    }

    /**
     * Runs the scoring loop for each player
     * Dealer is excluded as they do not wager and make points from players losing
     */
    public void scoreGame() {
        for (FaroPlayer player : players) {
            if (!player.getIsDealer() ) {
                scorePlayer(player);
            }
        }
        updateUserPointsLabel();
        npcOneTotal.setText(String.valueOf(players[1].getPoints()));
        npcTwoTotal.setText(String.valueOf(players[2].getPoints()));
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
            JOptionPane.showMessageDialog (null, player.getName() + " lost " + losingWager);
        }

        // Adds winnings to winning card
        int winningWager = player.getWagerPoints(winningCard);
        if (winningWager > 0) {
            dealer.subtractPoints(winningWager);
            player.addPoints(winningWager * 2); // Hands back wager and pays 1:1
            player.clearWager(winningCard);
            JOptionPane.showMessageDialog (null, player.getName() + " won " + winningWager);
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
        System.out.println("Three cards left:\n" + casekeep.output() + "\n\nGuess the cards in order:");
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
        scoreFinalThree(guess, order, player.getAmountToWager(), player);
    }

    public void openCasekeep() throws IOException {
        FXMLLoader casekeepLoader = new FXMLLoader(getClass().getResource("/fxmls/faro_casekeep.fxml"));
        Parent root = casekeepLoader.load();
        Stage stage2 = new Stage();
        stage2.setTitle("Casekeep");
        stage2.setScene(new Scene(root));
        stage2.show();
        CasekeepController casekeepController = casekeepLoader.getController();
        casekeepController.setCasekeep(casekeep);
    }

    public void openCpuBets(ActionEvent event) throws IOException {
        // Loads table stage
        FXMLLoader tableLoader = new FXMLLoader(getClass().getResource("/fxmls/faro_table_npc.fxml"));
        Parent root = tableLoader.load();
        Stage stage2 = new Stage();
        stage2.setTitle("Table");
        stage2.setScene(new Scene(root));
        stage2.show();

        // Passes NPC to controller
        TableController tableController = tableLoader.getController();
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.equals(leftPlayerBets)) {
            tableController.setPlayer(players[1]);
        } else {
            tableController.setPlayer(players[2]);
        }
    }

    public void openPlayerBets() throws IOException {
        FXMLLoader playerTableLoader = new FXMLLoader(getClass().getResource("/fxmls/faro_table_player.fxml"));
        Parent root = playerTableLoader.load();
        Stage stage2 = new Stage();
        stage2.setTitle("Your Table");
        stage2.setScene(new Scene(root));
        stage2.show();

        // Passes user player to controller
        TableController tableController = playerTableLoader.getController();
        tableController.setPlayer(players[0]);
    }

    public void updateUserPointsLabel() {
        userPointsLabel.setText(user.getUsername() + " - " + players[0].getPoints());
    }

    /**
     * Following 3 are used for testing only
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