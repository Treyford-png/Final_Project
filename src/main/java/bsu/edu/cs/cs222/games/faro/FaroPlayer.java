package bsu.edu.cs.cs222.games.faro;
import bsu.edu.cs.cs222.libraries.cards.CardDeck;

import java.util.HashMap;
import java.util.Map;

public class FaroPlayer {
    private final String name;
    private int points;
    private boolean isDealer;
    private final Map<String, Integer> wagers;

    public FaroPlayer(String name, int points) {
        this.name = name;
        this.points = points;
        isDealer = false;
        wagers = new HashMap<>();
        populateWagers();
    }

    public String getName() {
        return name;
    }

    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
    }

    public int subtractPoints(int pointsToSubtract) {
        points = Math.max(points - pointsToSubtract, 0);
        return pointsToSubtract;
    }
    public int getPoints() {
        return points;
    }

    public void populateWagers() {
        wagers.put("2", 0);
        wagers.put("3", 0);
        wagers.put("4", 0);
        wagers.put("5", 0);
        wagers.put("6", 0);
        wagers.put("7", 0);
        wagers.put("8", 0);
        wagers.put("9", 0);
        wagers.put("10", 0);
        wagers.put("j", 0);
        wagers.put("q", 0);
        wagers.put("k", 0);
        wagers.put("a", 0);

    }
    public void clearWager(String key) {
        wagers.put(key, 0);
    }

    public int getWagerPoints(String key) {
        return wagers.get(key);
    }

    public void addToWager(String key, int amount) {
        int current = wagers.get(key.toLowerCase());
        points -= amount;
        int amountToAdd = current + amount;
        wagers.put(key, amountToAdd);
    }

    public void setDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public boolean getIsDealer() {
        return isDealer;
    }

    public String placeWager(CardDeck deck) {
        return "";
    }

    public int getAmountToWager() {
        return 50;
    }

    public int getAmountPlaced() {
        int amount = 0;
        for (String key : wagers.keySet()) {
            amount += wagers.get(key);
        }
        return amount;
    }

    public String getWagers() {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        for (String key : wagers.keySet()) {
            output.append("[").append(key).append("]").append(" - ").append(wagers.get(key));
            if (counter % 4 == 0) {
                output.append("\n");
            } else {
                output.append(", ");
            }
            counter++;
        }
        return output.substring(0, output.length() - 2); // truncates last comma-space
    }
}
