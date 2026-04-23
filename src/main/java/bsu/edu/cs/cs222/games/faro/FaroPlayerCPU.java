package bsu.edu.cs.cs222.games.faro;

import bsu.edu.cs.cs222.libraries.cards.*;

import java.util.Random;

/**
 * Extended version of FaroPlayer that implements a placeWager for a CPU and adds a Random
 *
 * @author Holden Hankins
 */
public class FaroPlayerCPU extends FaroPlayer {
    Random random;
    Casekeep casekeep;
    public FaroPlayerCPU(String name, int points) {
        super(name, points);
        random = new Random();
    }
    /**
     * AI logic for placing a wager
     */
    @Override
    public String placeWager(CardDeck deck) {
        int amount = 50;
        int deckSize = deck.getDeck().size();
        Card cardToBetOn = deck.getAt(random.nextInt(deckSize));
        String key = GetCardKey.getCardKey(cardToBetOn);
        // Used for simplified terminal version of iteration 1
        if (casekeep == null) {
            addToWager(key, amount);
            return ("Placed " + amount + " on " + key);
        }

        // Does not place on empty cards
        if (casekeep.get(key) != 0) {
            addToWager(key, amount);
            return ("Placed " + amount + " on " + key);
        }
        addToWager(key, amount);
        return ("No new wager was placed by " + getName());
    }
}
