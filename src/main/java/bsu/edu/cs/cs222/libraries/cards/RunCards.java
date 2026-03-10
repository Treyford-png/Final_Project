package bsu.edu.cs.cs222.libraries.cards;

public class RunCards {
    static void main() {
        CardDeck deckOfCards = new CardDeck();
        System.out.println(deckOfCards.getOutput());
        deckOfCards.shuffle();
        System.out.println(deckOfCards.getOutput());
        deckOfCards.shuffle();
        System.out.println(deckOfCards.getOutput());
    }
}
