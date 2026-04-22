package bsu.edu.cs.cs222.games.liberty_bell;

import bsu.edu.cs.cs222.characters.User;
import java.util.Objects;
import java.util.Random;

/**
 * The Liberty Bell slot machine itself. Contains all actions carried out by the machine,
 * namely spinning the reels and calculating payout
 */
public final class LibertyBellMachine {
    private final LibertyBellSymbols[] strip;
    private final Random rng;
    private int bet = 1;
    User user;

    public LibertyBellMachine() {
        this(new Random(), LibertyBellSymbols.values());
    }

    public LibertyBellMachine(User user) {
        this.user = user;
        this.rng = new Random();
        this.strip = LibertyBellSymbols.values();
    }

    /**
     * @param rng Random(rng) obj
     * @param strip all symbols that can appear
     */
    public LibertyBellMachine(Random rng, LibertyBellSymbols[] strip) {
        this.rng = Objects.requireNonNull(rng, "rng");
        this.strip = (strip == null || strip.length == 0) ? LibertyBellSymbols.values() : strip.clone();
    }

    /**
     * Generates a random symbol on each of the reels
     * @return 3 random LibertyBellSymbols enums
     */
    public LibertyBellSymbols[] spin() {
        LibertyBellSymbols[] result = new LibertyBellSymbols[3];
        for (int i = 0; i <3; i++) {
            result[i] = strip[rng.nextInt(strip.length)];
        }
        return result;
    }

    /**
     * Calculates the payout given 3 reels that the user should recieve
     * @param result array[3] of LibertyBellSymbols
     * @return
     */
    public int calculatePayout(LibertyBellSymbols[] result) {
        // Ensures that a valid result has been generated
        if (result == null || result.length != 3) {
            throw new IllegalArgumentException("result must be length 3");
        }

        LibertyBellSymbols a = result[0], b = result[1], c = result[2]; // splits results for parsing
        if (a == b && b == c) { // All 3 matching
            return a.getPoints();
        } else if (a == b || b == c || a == c ) { // 2 matching
            LibertyBellSymbols matched = (a == b || a== c) ? a : b;
            return matched.getPoints() / 2;
        }
        return 0; // none matching
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        // Ensures bet is positive
        if (bet < 0) {
            throw new IllegalArgumentException("bet > 0");
        }
        this.bet = bet;
    }
}