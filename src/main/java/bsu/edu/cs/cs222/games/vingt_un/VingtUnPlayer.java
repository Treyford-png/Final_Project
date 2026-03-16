package bsu.edu.cs.cs222.games.vingt_un;

import bsu.edu.cs.cs222.characters.*;
import bsu.edu.cs.cs222.libraries.cards.*;

import java.util.Scanner;

import static bsu.edu.cs.cs222.games.vingt_un.HandStatus.NATURAL_21;


public class VingtUnPlayer {
    private final String name;
    private int points;
    private int wager;
    private Hand hand;
    private boolean isDealer;
    private final boolean isUser;
    private boolean isWinner;
    private final Scanner vuPlayerscanner = new Scanner(System.in);

    public VingtUnPlayer(String name, int points, boolean isUser) {
        this.name = name;
        this.points = points;
        this.isUser = isUser;
        isDealer = false;
        wager = 0;
        hand = new Hand();
        isWinner = false;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setIsDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setWager(int wager) {
        this.wager = Math.min(wager, points); // Prevents wager from being more than total points
        points -= this.wager;
    }

    public int getWager() {
        return wager;
    }

    public void doubleWager() {
        wager = wager * 2;
        if (wager > points) {
            wager = points;
            points = 0;
        }
        else {
            points -= wager / 2;
        }
    }

    public void addPoints(int newPoints) {
        points += newPoints;
    }

    public void hit(CardDeck deckOfCards) {
        hand.addCard(deckOfCards.deal());
    }

    public Card drawCard(CardDeck deckOfCards) {
        return deckOfCards.deal();
    }

    public void endRound(boolean won) {
        if (won) {
            points += wager;
        }
        hand = new Hand();
    }

    /**
     * Controls the AI for NPCs
     * AI hits until its risk tolerance is reached or breaks 21
     */
    public void aiTurn(CardDeck deck, int riskFactor) {
        System.out.println(name + "\n[?] [?]"); // Hides cards
        if (hand.getHandStatus() == NATURAL_21) {
            System.out.println("Stand"); // Hides if that player has a 21
            return;
        }

        while (hand.value() < riskFactor && hand.value() != 0) {
            // Deals new card to player to reach riskFactor
            System.out.println("Hit");
            hit(deck);
            for (Card card : hand.getHand()) {
                System.out.print("[?] ");
            }

            // Handles AI's hand exceeding 21
            if (hand.value() > 21) {
                if(hand.checkForBust()) {
                    System.out.println(getName() + " throws up their cards");
                    hand.setValueToZero();
                    return;
                }
            }
        } // end while

        // If riskFactor is met, AI stands
        System.out.println("Stand");
    }

    /**
     * Allows a player to complete their turn
     * For each turn, game displays their hand then asks to hit or stand
     * Invalid inputs handled by ending turn
     */
    public void userTurn(CardDeck deck) {
        System.out.println(getHand().getOutput());
        if (hand.getHandStatus() == NATURAL_21) { // Rules do not allow for a hit on a nat 21
            return;
        }
        int userInput;
        while (hand.value() <= 21) {
            userInput = userActionPrompt();
            if (userInput == 1) { // HIT
                hit(deck);
            }
            else if (userInput == 2) { // STAND
                break;
            }
            else { // INVALID
                System.out.println("Invalid input. Ending turn");
                break;
            }
        }
        System.out.println(hand.getOutput());

        if (hand.value() > 21) {
            System.out.println("You throw up your cards");
            hand.setValueToZero();
        }
    }

    /**
     * Allows the user to choose if they hit or stand
     * @return user's input
     */
    public int userActionPrompt() {
        System.out.print("Your current hand is: ");
        System.out.println(hand.getOutput());
        System.out.print("Would you like to (1) HIT or (2) STAND: ");
        return vuPlayerscanner.nextInt(); // invalid input handled in userTurn
    }

    public void setWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void wagerPrompt() {
        System.out.println("You have " + points + " points. How much would you like to wager: ");
        int userWager = vuPlayerscanner.nextInt();
        if (userWager >= points) {
            System.out.println("Would you like to go all in for " + points +"? (0) No, (1) Yes: ");
            if (vuPlayerscanner.nextInt() == 1) {
                setWager(points);
            }
            else {
                wagerPrompt();
            }
        }
        else {
            setWager(userWager);
        }
    }
}