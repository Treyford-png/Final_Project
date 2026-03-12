package bsu.edu.cs.cs222.libraries.cards;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class CardDeckTest {
    @Test
    public void testDeckOfCards() {
        CardDeck deckOfCards = new CardDeck();
        ArrayList<Card> cards = deckOfCards.getDeck();
        String previousShuffle;
        String currentShuffle;
        deckOfCards.shuffle();
        currentShuffle = deckOfCards.getOutput();

        // Loops for an arbitrarily long period of time
        for (int i = 0; i < 10000; i++) {
            previousShuffle = currentShuffle;
            deckOfCards.shuffle();
            // Verify cards shuffled
            currentShuffle = deckOfCards.getOutput();
            assert(!currentShuffle.equals(previousShuffle));
        }
    }
}