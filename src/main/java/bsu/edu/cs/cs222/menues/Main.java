package bsu.edu.cs.cs222.menues;

import bsu.edu.cs.cs222.libraries.cards.*;

public class Main {
    static void main() {
        CardDeck deck = new CardDeck();
        System.out.println(deck.getOutput());
        System.out.println("\n---------\n");
        deck.shuffle();
        System.out.println(deck.getOutput());
    }
}