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
        random = new Random();
    }

    public void runGame() {
        startGame();
        userTurn();
        System.out.println("\nJohn:");
        aiTurn(player2);
        System.out.println("\nArthur:");
        aiTurn(player3);
        System.out.println("\n" + getWinner().getName() + " Wins!!!!");
    }

    public VingtUnPlayer getPlayer2() {
        return player2;
    }

    public void startGame() {
        deck.shuffle();
        userPlayer.getHand().firstTwoCards(deck);
        player2.getHand().firstTwoCards(deck);
        player3.getHand().firstTwoCards(deck);
    }

    public VingtUnPlayer getPlayer3() {
        return player3;
    }

    public void aiTurn(VingtUnPlayer player) {
        if (player.getHand().getHandStatus() == NATURAL_21) {
            return;
        }
        System.out.println(player.getHand().getOutput());
        int rng = 15 + (random.nextInt() % 3);

        while (player.getHand().getHandValue() < rng && player.getHand().getHandValue() != 0) {
            System.out.println("Hit");
            player.hit(deck);
            System.out.println(player.getHand().getOutput());
            if (player.getHand().getHandValue() > 21) {
                if(player.getHand().checkForBust()) {
                    System.out.println(player.getName() + "'s hand busts");
                    return;
                }
            }
        }
        System.out.println("Stay");
    }

    public void userTurn() {
        System.out.println(userPlayer.getHand().getOutput());
        if (userPlayer.getHand().getHandStatus() == NATURAL_21) {
            return;
        }
        int userInput;
        while (userPlayer.getHand().getHandValue() < 21 && userPlayer.getHand().getHandValue() != 0) {
            userInput = askPlayerToHit();
            if (userInput == 1) {
                userPlayer.hit(deck);
            }
            else if (userInput == 2) {
                break;
            }
            else {
                System.out.println("Invalid input. Ending turn");
            }
        }
        System.out.println(userPlayer.getHand().getOutput());

        if (userPlayer.getHand().getHandValue() == 0) {
            System.out.println("Your hand busts");
        }
    }

    public int askPlayerToHit() {
        System.out.print("Your current hand is: ");
        for (Card card : userPlayer.getHand().getHand()) {
            System.out.print(card.getShortName() + " ");
        }
        System.out.println("which has a value of " + userPlayer.getHand().getHandValue());
        System.out.print("Would you like to (1) HIT or (2) STAY: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public VingtUnPlayer getWinner() {
        VingtUnPlayer winner = userPlayer;
        if (winner.getHand().getHandValue() < player2.getHand().getHandValue()) {
            winner = player2;
        }
        if (winner.getHand().getHandValue() < player3.getHand().getHandValue()) {
            winner = player3;
        }
        return winner;
    }
}