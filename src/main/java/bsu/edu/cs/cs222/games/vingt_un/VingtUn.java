package bsu.edu.cs.cs222.games.vingt_un;
import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.libraries.cards.*;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Scanner;

public class VingtUn {
    private final User user;
    private final VingtUnPlayer[] players;
    private CardDeck deck;
    private final ArrayList<Card> removedCards;
    private final Scanner vuScanner = new Scanner(System.in);

    public VingtUn(User user) {
        this.user = user;
        VingtUnPlayer userPlayer = new VingtUnPlayer(user.getUsername(), user.getPoints(), true);
        VingtUnPlayer player2 = new VingtUnPlayer("John", 5000, false);
        VingtUnPlayer player3 = new VingtUnPlayer("Arthur", 5000, false);
        players = new VingtUnPlayer[]{userPlayer, player2, player3};
        deck = new CardDeck();
        removedCards = new ArrayList<>();
        player2.setIsDealer(true);
    }

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

    public VingtUnPlayer determineDealer(Label userLabel, Label npc1Label, Label npc2Label) {
        getDealer().setIsDealer(false);
        System.out.println("First player to draw an ace is the dealer");
        Scanner scanner = new Scanner(System.in);
        Card card;
        while (!deck.getDeck().isEmpty()) {
            // Player draws card
            System.out.println("Press any number to draw a card");
            scanner.nextInt();
            card = deck.deal();
            System.out.println("[" + card.getShortName() + "]");
            if (card.getValue() == 11) {
                players[0].setIsDealer(true);
                return players[0];
            }

            // Players 2 and 3 draw cards
            for (int i = 1; i < 3; i++) {
                card = deck.deal();
                System.out.println(players[i].getName() + ": [" + card.getShortName() + "]");
                if (card.getValue() == 11) {
                    players[i].setIsDealer(true);
                    return players[i];
                }
            }
        } // end for
        return null;
    }

    public void scoreGame() {
        VingtUnPlayer dealer = getDealer();
        for (VingtUnPlayer player : players) {
            if (!player.isDealer()) {
                calculateWinnings(player, dealer);
            }
        }
    }

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

    public void clearHands() {
        for (VingtUnPlayer player : players) {
            player.getHand().newHand();
        }
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public void ensureDeckHas(int cards) {
        if (deck.getDeck().size() < cards) {
            newDeck();
        }
    }

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

    public VingtUnPlayer getDealer() {
        for (VingtUnPlayer player : players) {
            if (player.isDealer()) {
                return player;
            }
        }
        return null;
    }

    public String winnings(int oldPoints, VingtUnPlayer player) {
        int newPoints = player.getPoints();
        if (oldPoints > newPoints) {
            return player.getName() + " lost " + (oldPoints - newPoints) + " points";
        }
        else {
            return player.getName() + " made " + (newPoints - oldPoints) + " points";
        }
    }

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

    public void endGame() {
        int pointsEarned = players[0].getPoints() - user.getPoints();
        user.addPoints(pointsEarned);
        user.savePoints();
    }
}