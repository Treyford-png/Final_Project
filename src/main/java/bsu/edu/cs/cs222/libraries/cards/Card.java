package bsu.edu.cs.cs222.libraries.cards;

public class Card {
    private final String shortName;
    private final String name;
    private final Suit suit;
    private final double value;
    private int rng;

    Card(String shortName, String name, Suit suit, double value) {
        this.shortName = shortName;
        this.name = name;
        this.suit = suit;
        this.value = value;
        rng = 0;
    }

    public void setRNG(int rng) {
        this.rng = rng;
    }

    public String getShortName() {
        return shortName;
    }

    public double getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getRNG() {
        return rng;
    }

    public String getName() {
        return name;
    }
}
