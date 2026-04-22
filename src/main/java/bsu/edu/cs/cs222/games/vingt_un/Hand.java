package bsu.edu.cs.cs222.games.vingt_un;

import bsu.edu.cs.cs222.libraries.cards.Card;
import bsu.edu.cs.cs222.libraries.cards.CardDeck;

import java.util.ArrayList;
import static bsu.edu.cs.cs222.games.vingt_un.HandStatus.*;

/**
 * An object that contains a hand of cards for vingt-un
 * ArrayList - cards in hand
 * handValue - added together value of all cards(0 if busted)
 * handStatus - if the hand is busted, 21, or in play
 *
 * @author Holden Hankins
 */
public class Hand {
    private final ArrayList<Card> hand;
    private int handValue;
    private HandStatus handStatus;

    public Hand() {
        hand = new ArrayList<>();
        handValue = 0;
        handStatus = OK;
    }

    public int value() {
        return handValue;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public HandStatus getHandStatus() {
        return handStatus;
    }

    public void setValueToZero() {
        handValue = 0;
    }

    public int getHandValue() {
        return handValue;
    }

    /**
     * Adds a card to hand and checks if it is over 21
     * @param card Card to add
     */
    public void addCard(Card card) {
        hand.add(card);
        handValue += (int) card.getValue();
        if (handValue > 22) {
            checkForBust();
        }
    }

    /**
     * In order to get a hand below 21, the game tries to find an ace it can turn from an 11 to a 1
     * @return if a card got lowered
     */
    public boolean tryConvertAce() {
        for (Card card : hand) {
            if (card.tryLowerAce()) {
                handValue -= 10;
                return true;
            }
        }
        return false;
    } // Close convertAces()

    /**
     * Checks if a hand is busted even when accounting for aces
     */
    public boolean checkForBust() {
        if (handValue > 21) {
            // Try turning an 11 into a 1 to resolve issue
            if (!tryConvertAce()) {
                handStatus = BUSTED;
                return true;
            }
        } // Close if
        return false;
    }

    /**
     * Deals 2 cards from a deck to a player face down
     */
    public void firstTwoCards(CardDeck deck) {
        hand.add(deck.deal());
        hand.add(deck.deal());
        handValue += (int) (hand.get(0).getValue() + hand.get(1).getValue());
        if (handValue == 21) {
            handStatus = NATURAL_21;
        }
        else if (handValue == 22) { // Only possible with 2 aces
            checkForBust();
        }
    }

    /**
     * Creates a fresh new hand
     */
    public void newHand() {
        hand.clear();
        handValue = 0;
        handStatus = OK;
    }

    /**
     * @return a string formated "[#] [#] which has a value of #"
     */
    public String getOutput() {
        StringBuilder returner = new StringBuilder();
        returner.append(" "); // Offsets the extra space added during the loop
        for (Card card : hand) {
            returner.append("[").append(card.getShortName()).append("] ");
        }
        return returner.toString();
    }
}