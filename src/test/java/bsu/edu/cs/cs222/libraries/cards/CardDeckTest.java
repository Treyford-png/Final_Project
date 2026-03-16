package bsu.edu.cs.cs222.libraries.cards;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static bsu.edu.cs.cs222.libraries.cards.Suit.CLUBS;


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

    @Test
    public void testDealCard() {
        CardDeck deckOfCards = new CardDeck();
        deckOfCards.shuffle();
        Card currentCard;
        Card previousCard;
        Card topCard;

        topCard = deckOfCards.lookAtTop();
        currentCard = deckOfCards.deal();
        assert (topCard.equals(currentCard));
        for (int i = 1; i < 52; i++) {
            previousCard = currentCard;
            currentCard = deckOfCards.deal();
            System.out.println(previousCard.getShortName() + " " + currentCard.getShortName());
            assert !(previousCard.equals(currentCard));
        }

        currentCard = deckOfCards.deal();
        assert currentCard == null;
    }

    @Test
    public void testRemoveCard() {
        Card card = new Card("10c", "10", CLUBS, 10);
        CardDeck deck = new CardDeck();
        deck.removeCard(card);
        for (int i = 0; i < 51; i++) {
            assert(!deck.deal().equals(card));
        }
    }
}