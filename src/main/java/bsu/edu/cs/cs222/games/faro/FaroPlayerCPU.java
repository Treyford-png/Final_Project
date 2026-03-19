package bsu.edu.cs.cs222.games.faro;

import bsu.edu.cs.cs222.libraries.cards.*;

import java.util.Random;

public class FaroPlayerCPU extends FaroPlayer {
    Random random;
    public FaroPlayerCPU(String name, int points) {
        super(name, points);
        random = new Random();
    }

    /**
     * AI logic for placing a wager
     * Hoping to improve in later iterations
     */
    @Override
    public String placeWager(CardDeck deck) {
        int amount = 50;
        int deckSize = deck.getDeck().size();
        Card cardToBetOn = deck.getAt(random.nextInt(deckSize));
        String key = GetCardKey.getCardKey(cardToBetOn);
        addToWager(key, amount);
        return ("Placed " + amount + " on " + key);
    }
}
