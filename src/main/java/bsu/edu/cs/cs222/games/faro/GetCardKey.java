package bsu.edu.cs.cs222.games.faro;

import bsu.edu.cs.cs222.libraries.cards.Card;

public class GetCardKey {
    public static String getCardKey(Card card) {
        String key;
        double value = card.getValue();

        if (value > 10) { // Handle face cards and aces
            if (value == 10.1) {
                key = "j";
            }
            else if (value == 10.2) {
                key = "q";
            }
            else if (value == 10.3) {
                key = "k";
            }
            else {
                key = "a";
            }
        }
        else { // uses value as key
            key = Integer.toString((int) value);
        }
        return key;
    }
}
