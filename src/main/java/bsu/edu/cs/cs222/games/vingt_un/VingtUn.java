package bsu.edu.cs.cs222.games.vingt_un;
import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.libraries.cards.*;
import static bsu.edu.cs.cs222.games.vingt_un.HandStatus.*;

import java.util.Random;
import java.util.Scanner;

public class VingtUn {
    private final Random random;
    private final User user;
    private final VingtUnPlayer userPlayer;
    private final VingtUnPlayer player2;
    private final VingtUnPlayer player3;
    private final CardDeck deck;

    public VingtUn(User user) {
        this.user = user;
        userPlayer = new VingtUnPlayer(user.getUsername(), user.getPoints(), true);
        player2 = new VingtUnPlayer("John", 5000, false);
        player3 = new VingtUnPlayer("Arthur", 5000, false);
        deck = new CardDeck();
        random = new Random(); // Used to generate riskFactor
    }

    public void runGame() {
        startGame();

        // Turns
        userTurn();
        System.out.println("\nJohn:");
        aiTurn(player2);
        System.out.println("\nArthur:");
        aiTurn(player3);

        // Reveal
        System.out.println("You: " + userPlayer.getHand().getOutput());
        System.out.println("John: " + player2.getHand().getOutput());
        System.out.println("Arthur: " + player3.getHand().getOutput());
        System.out.println("\n" + getWinner().getName() + " is the winner!!!!");
    }

    public void startGame() {
        deck.shuffle();
        userPlayer.getHand().firstTwoCards(deck);
        player2.getHand().firstTwoCards(deck);
        player3.getHand().firstTwoCards(deck);
    }

    public VingtUnPlayer getPlayer2() {
        return player2;
    }

    public VingtUnPlayer getPlayer3() {
        return player3;
    }

    /**
     * Controls the AI for NPCs
     * AI hits until its risk tolerance is passed or breaks 21
     */
    public void aiTurn(VingtUnPlayer player) {
        System.out.println("[?] [?]"); // Hides cards
        if (player.getHand().getHandStatus() == NATURAL_21) {
            System.out.println("Stay"); // Hides if that player has a 21
            return;
        }
        // Number AI will stop on
        int riskFactor = 15 + (random.nextInt() % 3);

        while (player.getHand().getHandValue() < riskFactor && player.getHand().getHandValue() != 0) {
            // Deals new card to player to reach riskFactor
            System.out.println("\nHit");
            player.hit(deck);
            for (Card card : player.getHand().getHand()) {
                System.out.print("[?] ");
            }

            // Handles AI's hand exceeding 21
            if (player.getHand().getHandValue() > 21) {
                if(player.getHand().checkForBust()) {
                    System.out.println(player.getName() + "'s hand busts");
                    player.getHand().setValueToZero();
                    return;
                }
            }
        }

        // If riskFactor is met, AI stands
        System.out.println("Stand");
    }

    /**
     * Allows a player to complete their turn
     * For each turn, game displays their hand then asks to hit or stand
     * Invalid inputs handled by ending turn
     */
    public void userTurn() {
        System.out.println(userPlayer.getHand().getOutput());
        if (userPlayer.getHand().getHandStatus() == NATURAL_21) { // Rules do not allow for a hit on a nat 21
            return;
        }
        int userInput;
        while (userPlayer.getHand().getHandValue() <= 21) {
            userInput = userActionPrompt();
            if (userInput == 1) { // HIT
                userPlayer.hit(deck);
            }
            else if (userInput == 2) { // STAND
                break;
            }
            else { // INVALID
                System.out.println("Invalid input. Ending turn");
                break;
            }
        }
        System.out.println(userPlayer.getHand().getOutput());

        if (userPlayer.getHand().getHandValue() > 21) {
            System.out.println("Your hand busts");
            userPlayer.getHand().setValueToZero();
        }
    }

    /**
     * Allows the user to choose if they hit or stand
     * @return user's input
     */
    public int userActionPrompt() {
        System.out.print("Your current hand is: ");
        System.out.println(userPlayer.getHand().getOutput());
        System.out.print("Would you like to (1) HIT or (2) STAND: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt(); // invalid input handled in userTurn
    }

    public VingtUnPlayer getWinner() {
        // TODO: handle ties
        VingtUnPlayer winner = userPlayer;
        if (winner.getHand().getHandValue() < player2.getHand().getHandValue()) {
            winner = player2;
        }
        if (winner.getHand().getHandValue() < player3.getHand().getHandValue()) {
            winner = player3;
        }
        return winner;
    }

    public void clearHands() {
        userPlayer.getHand().newHand();
        player2.getHand().newHand();
        player3.getHand().newHand();
    }

    public void shuffleDeck() {
        deck.shuffle();
    }
}