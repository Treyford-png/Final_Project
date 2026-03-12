package bsu.edu.cs.cs222.games.vingt_un;

import bsu.edu.cs.cs222.libraries.cards.Card;
import bsu.edu.cs.cs222.libraries.cards.CardDeck;

import java.util.ArrayList;
import static bsu.edu.cs.cs222.games.vingt_un.HandStatus.*;

public class Hand {
    private final ArrayList<Card> hand;
    private int handValue;
    private HandStatus handStatus;

    public Hand() {
        hand = new ArrayList<>();
        handValue = 0;
        handStatus = OK;
    }

    public int getHandValue() {
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

    public Card getCard(int i) {
        if (i >= hand.size()) {
            return null;
        }
        return hand.get(i);
    }

    public void addCard(Card card) {
        hand.add(card);
        handValue += (int) card.getValue();
        if (handValue > 22) {
            checkForBust();
        }
    }

    public boolean tryConvertAce() {
        for (Card card : hand) {
            if (card.tryLowerAce()) {
                handValue -= 10;
                return true;
            }
        }
        return false;
    } // Close convertAces()

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

    public void newHand() {
        hand.clear();
        handValue = 0;
    }

    public String getOutput() {
        StringBuilder returner = new StringBuilder();
        for (Card card : hand) {
            returner.append("[").append(card.getShortName()).append("] ");
        }
        returner.append("which has a value of ").append(handValue);
        return returner.toString();
    }
}