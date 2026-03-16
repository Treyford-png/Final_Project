package bsu.edu.cs.cs222.libraries.cards;

public class RunCards {
    static void main() {
        CardDeck deckOfCards = new CardDeck();
        deckOfCards.shuffle();
        System.out.println(deckOfCards.getOutput());
        Card card;
        for (int i = 0; i < 52; i++) {
            card = deckOfCards.deal();
            System.out.print(card.getShortName() + ", ");
        }
    }
}