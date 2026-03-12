package bsu.edu.cs.cs222.games.vingt_un;

import bsu.edu.cs.cs222.characters.*;
import bsu.edu.cs.cs222.libraries.cards.*;


public class VingtUnPlayer {
    private final String name;
    private int points;
    private int wager;
    private Hand hand;
    private boolean isDealer;
    private final boolean isUser;

    public VingtUnPlayer(String name, int points, boolean isUser) {
        this.name = name;
        this.points = points;
        this.isUser = isUser;
        isDealer = false;
        wager = 0;
        hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setIsDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setWager(int wager) {
        this.wager = Math.min(wager, points);
        points -= this.wager;
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
}
