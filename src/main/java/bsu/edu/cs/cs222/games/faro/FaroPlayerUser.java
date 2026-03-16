package bsu.edu.cs.cs222.games.faro;

import bsu.edu.cs.cs222.libraries.cards.CardDeck;

import java.util.Scanner;
import java.util.Set;

public class FaroPlayerUser extends FaroPlayer {
    Scanner faroScanner;

    public FaroPlayerUser(String name, int points) {
        super(name, points);
    }

    @Override
    public String placeWager(CardDeck deck) {
        faroScanner = new Scanner(System.in);
        String card = getCard();
        if (card.equals("x")) {
            return "No new wager placed";
        }
        int amount = 0;
        while (!card.equals("x")) {
            amount = getAmountToWager();
            addToWager(card, amount);
            System.out.println("You may place another wager");
            faroScanner = new Scanner(System.in);
            card = getCard();
        }
        return "You wagered " + amount + " on "  + card;
    }

    @Override
    public int getAmountToWager() {
        System.out.print("How much would you like to wager? ");
        int amount = faroScanner.nextInt();
        System.out.println();
        if (amount >= getPoints()) {
            int input;
            System.out.print("Would you like to go all in for " + getPoints() + "?\n(1) Yes, (2) No: ");
            input = faroScanner.nextInt();
            if (input == 1) {
                return getPoints();
            }
            else {
                return getAmountToWager();
            }
        }
        return amount;
    }

    public String getCard() {
        System.out.print("Which card would you like to wager on? Press 'x' for none\nEnter number, face card's " +
                "first letter, or [h]igh card: ");
        String input = faroScanner.nextLine();
        if (input.equals("x")) {
            return "x";
        }
        Set<String> validInputs = Set.of("2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a", "h");
        if (!(validInputs.contains(input))) {
            System.out.println("Invalid input. Please give a number 2 through 10, j, q, k, or a");
            return getCard();
        }
        return input;
    }
}
