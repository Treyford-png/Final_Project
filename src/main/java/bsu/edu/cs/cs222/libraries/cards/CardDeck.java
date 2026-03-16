package bsu.edu.cs.cs222.libraries.cards;

import static bsu.edu.cs.cs222.libraries.cards.Suit.*;

import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
    private ArrayList<Card> deck = new ArrayList<>();
    Random random = new Random();

    public CardDeck() {
        deck.add(new Card("2h", "2", HEARTS, 2));
        deck.add(new Card("3h", "3", HEARTS, 3));
        deck.add(new Card("4h", "4", HEARTS, 4));
        deck.add(new Card("5h", "5", HEARTS, 5));
        deck.add(new Card("6h", "6", HEARTS, 6));
        deck.add(new Card("7h", "7", HEARTS, 7));
        deck.add(new Card("8h", "8", HEARTS, 8));
        deck.add(new Card("8h", "9", HEARTS, 9));
        deck.add(new Card("10h", "10", HEARTS, 10));
        deck.add(new Card("Jh", "Jack", HEARTS, 10.1));
        deck.add(new Card("Qh", "Queen", HEARTS, 10.2));
        deck.add(new Card("Kh", "King", HEARTS, 10.3));
        deck.add(new Card("Ah", "Ace", HEARTS, 11));
        deck.add(new Card("2d", "2", DIAMONDS, 2));
        deck.add(new Card("3d", "3", DIAMONDS, 3));
        deck.add(new Card("4d", "4", DIAMONDS, 4));
        deck.add(new Card("5d", "5", DIAMONDS, 5));
        deck.add(new Card("6d", "6", DIAMONDS, 6));
        deck.add(new Card("7d", "7", DIAMONDS, 7));
        deck.add(new Card("8d", "8", DIAMONDS, 8));
        deck.add(new Card("9d", "9", DIAMONDS, 9));
        deck.add(new Card("10d", "10", DIAMONDS, 10));
        deck.add(new Card("Jd", "Jack", DIAMONDS, 10.1));
        deck.add(new Card("Qd", "Queen", DIAMONDS, 10.2));
        deck.add(new Card("Kd", "King", DIAMONDS, 10.3));
        deck.add(new Card("Ad", "Ace", DIAMONDS, 11));
        deck.add(new Card("2c", "2", CLUBS, 2));
        deck.add(new Card("3c", "3", CLUBS, 3));
        deck.add(new Card("4c", "4", CLUBS, 4));
        deck.add(new Card("5c", "5", CLUBS, 5));
        deck.add(new Card("6c", "6", CLUBS, 6));
        deck.add(new Card("7c", "7", CLUBS, 7));
        deck.add(new Card("8c", "8", CLUBS, 8));
        deck.add(new Card("9c", "9", CLUBS, 9));
        deck.add(new Card("10c", "10", CLUBS, 10));
        deck.add(new Card("Jc", "Jack", CLUBS, 10.1));
        deck.add(new Card("Qc", "Queen", CLUBS, 10.2));
        deck.add(new Card("Kc", "King", CLUBS, 10.3));
        deck.add(new Card("Ac", "Ace", CLUBS, 11));
        deck.add(new Card("2s", "2", SPADES, 2));
        deck.add(new Card("3s", "3", SPADES, 3));
        deck.add(new Card("4s", "4", SPADES, 4));
        deck.add(new Card("5s", "5", SPADES, 5));
        deck.add(new Card("6s", "6", SPADES, 6));
        deck.add(new Card("7s", "7", SPADES, 7));
        deck.add(new Card("8s", "8", SPADES, 8));
        deck.add(new Card("9s", "9", SPADES, 9));
        deck.add(new Card("10s", "10", SPADES, 10));
        deck.add(new Card("Js", "Jack", SPADES, 10.1));
        deck.add(new Card("Qs", "Queen", SPADES, 10.2));
        deck.add(new Card("Ks", "King", SPADES, 10.3));
        deck.add(new Card("As", "Ace", SPADES, 11));
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Card lookAtTop() {
        return deck.getFirst();
    }

    public Card deal() {
        if (deck.isEmpty()) {
            return null;
        }
        Card card = deck.getFirst();
        deck.removeFirst();
        return card;
    }

    /**
     * Adds all cards to a new deck one a time at random
     * Once added to new deck, card is removed from old deck to keep space consistent
     */
    public void shuffle() {
        deck = new CardDeck().getDeck();
        ArrayList<Card> shuffledDeck = deck;
        for (int i = 51; i > 0; i--) {
            int index = random.nextInt(0, i);
            shuffledDeck.add(deck.get(index));
            deck.remove(index);
        }
        shuffledDeck.add(deck.getFirst());
        deck.removeFirst();

        for(int i = 0; i < 52; i++) {
            deck.add(shuffledDeck.getFirst());
            shuffledDeck.removeFirst(); // Prevents access required space
        }
    }

    /**
     * Assumes deck is not shuffled
     * Finds the default location of the requested card in the card
     * If not at that location, works backwards to find it
     * @param card to remove
     */
    public void removeCard(Card card) {
        int index = 0;
        // Shifts index to section containing matching suit
        switch (card.getSuit()) {
            case DIAMONDS:
                index += 13;
                break;
            case CLUBS:
                index += 26;
                break;
            case SPADES:
                index += 39;
        }

        // Handles face cards by adding the decimal weight * 10
        if (card.getValue() != (double) ((int) card.getValue())) {
            index += (int) ((card.getValue() % 10) * 10);
        }
        if (card.getValue() == 11) {
            index += 12;
        }
        else {
            index += (int) card.getValue() - 2;
        }

        // Works backwards to find card
        for (int i = index; i >=0; i--) {
            System.out.println(deck.get(i).getShortName());
            if (deck.get(i).equals(card)) {
                deck.remove(i);
                return;
            }
        }
    }

    public Card getAt(int index) {
        return deck.get(index);
    }

    /**
     * @return "[value][suit] " * n
     */
    public String getOutput() {
        String output = "";
        for (int i = 0; i < 52; i++) {
            output += (deck.get(i).getShortName() + " ");
        }
        return output;
    }
}