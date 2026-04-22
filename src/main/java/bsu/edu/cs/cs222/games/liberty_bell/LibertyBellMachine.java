package bsu.edu.cs.cs222.games.liberty_bell;

import bsu.edu.cs.cs222.games.liberty_bell.LibertyBellSymbols;
import java.util.Objects;
import java.util.Random;

public final class LibertyBellMachine {
    private final LibertyBellSymbols[] strip;
    private final Random rng;
    private int bet = 1;

    public LibertyBellMachine() {
        this(new Random(), LibertyBellSymbols.values());
    }

    public LibertyBellMachine(Random rng, LibertyBellSymbols[] strip) {
        this.rng = Objects.requireNonNull(rng, "rng");
        this.strip = (strip == null || strip.length == 0) ? LibertyBellSymbols.values() : strip.clone();
    }
    public LibertyBellSymbols[] spin() {
        LibertyBellSymbols[] result = new LibertyBellSymbols[3];
        for (int i = 0; i <3; i++) {
            result[i] = strip[rng.nextInt(strip.length)];
        }
        return result;
    }

    public int calculatePayout(LibertyBellSymbols[] result) {
        if (result == null || result.length != 3) {
            throw new IllegalArgumentException("result must be length 3");
        }

        LibertyBellSymbols a = result[0], b = result[1], c = result[2];
        if (a == b && b == c) {
            return a.getPoints();
        } else if (a == b || b == c || a == c ) {
            LibertyBellSymbols matched = (a == b || a== c) ? a : b;
        return matched.getPoints() / 2;
        }
        return 0;
    }

    //  compute as needed **

    public String getOutput(LibertyBellSymbols[] res) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LibertyBellSymbols  symbol : res) {
            stringBuilder.append("[").append(symbol.getDisplayName()).append("]");
        }
        return stringBuilder.toString();
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        if (bet <= 0) {
            throw new IllegalArgumentException("bet>0");
        }
        this.bet = bet;
    }

    public static final class SpinResult {
        public final LibertyBellSymbols[] symbols;
        public final int payout;

        public SpinResult(LibertyBellSymbols[] symbols, int payout) {
            this.symbols = symbols;
            this.payout = payout;
        }
    }
}