package bsu.edu.cs.cs222.games.vingt_un;
import bsu.edu.cs.cs222.characters.NPCs.NPC;
import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.libraries.cards.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Logic for the Vingt-Un (blackjack) game
 *
 * @author Holden Hankins
 */
public class VingtUn {
    private final User user;
    private CardDeck deck;
    private final ArrayList<Card> removedCards;
    private final Scanner vuScanner = new Scanner(System.in);
    private final VingtUnPlayer[] players;
    // Used to save NPCs
    private String player2Key;
    private String player3Key;

    public VingtUn(User user) {
        this.user = user;
        VingtUnPlayer userPlayer = new VingtUnPlayer(user.getUsername(), user.getPoints(), false);
        // Placeholders
        VingtUnPlayer player2 = new VingtUnPlayer("John", 5000, false);
        VingtUnPlayer player3 = new VingtUnPlayer("Arthur", 5000, false);
        players = new VingtUnPlayer[]{userPlayer, player2, player3};
        setNPCs();
        players[2].setIsDealer(true);
        deck = new CardDeck();
        removedCards = new ArrayList<>();
    }

    /**
     * Generates 2 random NPCs that you will play against
     */
    private void setNPCs() {
        // Generates 2 random indexes
        Random random = new Random();
        int number1 = random.nextInt(17);
        int number2 = random.nextInt(17);
        if (number1 == number2) {
            if (number1 == 16) {
                number1--;
            } else {
                number1++;
            }
        }

        // Sets corresponding NPCs
        NPC npc;
        npc = user.getAllNPCS().getNPCAtIndex(number1);
        players[1] = new VingtUnPlayer(npc.getName(), npc.getPoints(), false);
        player2Key = npc.getID();
        npc = user.getAllNPCS().getNPCAtIndex(number2);
        players[2] = new VingtUnPlayer(npc.getName(), npc.getPoints(), false);
        player3Key = npc.getID();
    }

    /**
     * Runs the game
     * Used for debugging and testing
     */
    public void runGame() {
        deck.shuffle();
        boolean playAgain = true;
        int[] oldPoints = {0, 0, 0};
        while (playAgain) {
            for(VingtUnPlayer player : players) {
                System.out.println(player.getName() + " - " + player.getPoints());
            }
            oldPoints[0] = players[0].getPoints();
            oldPoints[1] = players[1].getPoints();
            oldPoints[2] = players[2].getPoints();

            startGame();

            // Turns
            ensureDeckHas(5);
            players[0].userTurn(deck);
            System.out.println();
            ensureDeckHas(5);
            players[1].aiTurn(deck, 15);
            System.out.println();
            ensureDeckHas(5);
            players[2].aiTurn(deck, 17);
            System.out.println();

            // Reveal
            System.out.println("You: " + players[0].getHand().getOutput());
            System.out.println("John: " + players[1].getHand().getOutput());
            System.out.println("Arthur: " + players[2].getHand().getOutput());
            scoreGame();

            for (int i = 0; i < 3; i++) {
                System.out.println(winnings(oldPoints[i], players[i]));
            }
            // Prevent duplicate cards in reshuffled deck
            for (VingtUnPlayer player : players) {
                removedCards.addAll(player.getHand().getHand());
            }
            clearHands();

            for (VingtUnPlayer player : players) {
                if (player.getHand().getHandStatus() == HandStatus.NATURAL_21) {
                    deck = new CardDeck();
                    System.out.println(player.getName() + " is the new dealer\n");
                    break;
                }
            }

            if (checkForOuts()) {
                break;
            }
            int playAgainInt = playAgainPrompt();
            if (playAgainInt == 0) {
                playAgain = false;
            }
        }
        endGame();
    }

    /**
     * Begins the game and gives plays initial cards
     */
    public void startGame() {
        // Set wagers
        deck.shuffle();

        if (!players[1].isDealer()) {
            players[1].setWager(50);
        }
        if (!players[2].isDealer()) {
            players[2].setWager(50);
        }

        // Draws initial hand
        ensureDeckHas(6);
        players[0].getHand().firstTwoCards(deck);
        players[1].getHand().firstTwoCards(deck);
        players[2].getHand().firstTwoCards(deck);
    }

    public VingtUnPlayer getMainPlayer() {
        return players[0];
    }
    public VingtUnPlayer getPlayer2() {
        return players[1];
    }

    public VingtUnPlayer getPlayer3() {
        return players[2];
    }

    /**
     * Scores game
     * Only focuses on players who made bets (not dealer)
     */
    public void scoreGame() {
        VingtUnPlayer dealer = getDealer();
        for (VingtUnPlayer player : players) {
            if (!player.isDealer()) {
                calculateWinnings(player, dealer);
            }
        }
    }

    /**
     * Calculates how much a player wins by comparing it to the dealer
     * @param player player Player
     * @param dealer dealer Player
     */
    public void calculateWinnings(VingtUnPlayer player, VingtUnPlayer dealer) {
        System.out.println(players[1].getWager());
        // Beats Dealer
        if (player.getHand().value() > dealer.getHand().value()) {
            // Sets dealer's wager to amount needed to pay player
            dealer.setWager(player.getWager());
            if (player.getHand().value() == 21) {
                dealer.doubleWager();
            }
            // Readds wager and adds points won from dealer
            player.addPoints(player.getWager() + dealer.getWager());
            return;
        }

        // Loses to dealer
        if (player.getHand().value() < dealer.getHand().value() || player.getHand().value() == 0) {
            if (dealer.getHand().value() == 21 && player.getHand().value() > 0) {
                player.doubleWager();
            }
            dealer.addPoints(player.getWager());
            return;
        }

        // Ties dealer
        player.addPoints(player.getWager());
    }

    /**
     * Removes all cards from all users' hands
     */
    public void clearHands() {
        for (VingtUnPlayer player : players) {
            player.getHand().newHand();
        }
    }

    /**
     * Shuffles deck
     * Used for external calls
     */
    public void shuffleDeck() {
        deck.shuffle();
    }

    /**
     * If deck runs out of cards, it creates a new deck
     * @param cards number of cards
     */
    public void ensureDeckHas(int cards) {
        if (deck.getDeck().size() < cards) {
            newDeck();
        }
    }

    /**
     * Creates a new deck by reshuffles old cards back into the deck
     */
    public void newDeck() {
        for (VingtUnPlayer player : players) {
            removedCards.addAll(player.getHand().getHand());
        }

        deck = new CardDeck();
        for (Card card : removedCards) {
            deck.removeCard(card);
        }
        deck.shuffle();
        removedCards.clear();
        System.out.println("New deck has been created");
    }

    /**
     * Solely used for test cases
     */
    public CardDeck getDeck() {
        return deck;
    }

    /**
     * Gets the dealer
     * @return dealer
     */
    public VingtUnPlayer getDealer() {
        for (VingtUnPlayer player : players) {
            if (player.isDealer()) {
                return player;
            }
        }
        return null;
    }

    /**
     * Calculates the difference in points before and after game
     * @param oldPoints previous points
     * @param player player whose winnings you are calculating
     * @return difference
     */
    public String winnings(int oldPoints, VingtUnPlayer player) {
        int newPoints = player.getPoints();
        if (oldPoints > newPoints) {
            return player.getName() + " lost " + (oldPoints - newPoints) + " points";
        }
        else {
            return player.getName() + " made " + (newPoints - oldPoints) + " points";
        }
    }

    /**
     * Ask if the user wants to play again
     * @return binary yes/no
     */
    public int playAgainPrompt() {
        System.out.print("Would you like to play again? (0) No, (1) Yes: ");
        int input = vuScanner.nextInt();
        if (input != 0 && input != 1) {
            System.out.println("\nInvalid input");
            return playAgainPrompt();
        }
        else {
            return input;
        }
    }

    /**
     * Checks if a player has run out of money
     * If they have, crash() is called
     * @return a player's points are 0
     */
    public boolean checkForOuts() {
        for(VingtUnPlayer player : players) {
            if (player.getPoints() <= 0) {
                crashout(player);
                return true;
            }
        }
        return false;
    }

    /**
     * Keeping with the nature of the Wild West, if a player loses, a bar fight breaks out
     * If the dealer loses, they leave like the kid that owned the basketball in middle school
     * @param player that lost
     */
    public void crashout(VingtUnPlayer player) {
        if (player.isDealer()) { // Dealer lost
            System.out.println(player.getName() + " storms out of the saloon with the deck of cards");
            System.out.println("Everyone else shrugs and heads home for the night");
        }
        else { // A player lost
            System.out.println(player.getName() + " punches the dealer " + getDealer().getName());
            System.out.println(player.getName() + " is promptly escorted to a holding cell for the night");
            if (!player.getName().equals(user.getUsername())) {
                System.out.println("You see yourself out of the saloon to avoid any trouble");
            }
        }
    }

    /**
     * Saves all points and ends the game
     */
    public void endGame() {
        int pointsEarned = players[0].getPoints() - user.getPoints();
        user.addPoints(pointsEarned);
        user.savePoints();

        // Saves NPC points
        NPC npc = user.getAllNPCS().getNPC(player2Key);
        npc.addPoints(players[1].getPoints() - npc.getPoints());
        npc = user.getAllNPCS().getNPC(player3Key);
        npc.addPoints(players[2].getPoints() - npc.getPoints());
    }
}