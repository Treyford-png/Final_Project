package bsu.edu.cs.cs222.games.faro;

import java.util.HashMap;
import java.util.Map;

public class Casekeep {
    private final Map<String, Integer> casekeep;

    public Casekeep() {
        casekeep = new HashMap<>();
        populate();
    }

    public void populate() {
        casekeep.put("2", 4);
        casekeep.put("3", 4);
        casekeep.put("4", 4);
        casekeep.put("5", 4);
        casekeep.put("6", 4);
        casekeep.put("7", 4);
        casekeep.put("8", 4);
        casekeep.put("9", 4);
        casekeep.put("10", 4);
        casekeep.put("k", 4);
        casekeep.put("q", 4);
        casekeep.put("j", 4);
        casekeep.put("a", 4);
    }

    public int get(String key) {
        return casekeep.get(key);
    }

    /**
     * Removed card from the casekeep
     * @param key of a card in the HashMap
     */
    public void update(String key) {
        int numLeft = casekeep.get(key) - 1;
        casekeep.put(key, numLeft);
    }

    /**
     * Prints entire casekeep
     * @return formated [key] - #\n...
     */
    public String output() {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        for (String key : casekeep.keySet()) {
            output.append("[").append(key).append("]").append(" - ").append(casekeep.get(key));
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
