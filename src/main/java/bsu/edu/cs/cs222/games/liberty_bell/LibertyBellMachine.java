package bsu.edu.cs.cs222.games.liberty_bell;
import java.util.Objects;
import java.util.Random;
import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.liberty_bell.LibertyBellSymbols;

public class LibertyBellMachine {
    private final LibertyBellSymbols[] strip;
    private final Random rng;
    private final User user;
    private int bet = 1;

    public LibertyBellMachine(User user) {
        this(new Random(), LibertyBellSymbols.values(), user);
    }

    public LibertyBellMachine(Random rng, LibertyBellSymbols[] strip, User user) {
        this.rng = Objects.requireNonNull(rng, "rng");
        this.strip = (strip == null || strip.length == 0) ? LibertyBellSymbols.values() : strip.clone();
        this.user = Objects.requireNonNull(user, "user");
    }

    /**
     * Logic for spinning the roulette wheel
     * @return 3 symbols
     */

    public LibertyBellSymbols[] spin() {
        LibertyBellSymbols[] result = new LibertyBellSymbols[3];
        for (int i = 0; i < 3; i++) {
            result[i] = strip[rng.nextInt(strip.length)];
        }
        return result;
    }

    public int calculatePayout(LibertyBellSymbols[] result) {
        if (result == null || result.length != 3) {
            throw new IllegalArgumentException("result must exceed length 3");
        }
        LibertyBellSymbols a = result[0], b = result[1], c = result[2];
        if (a == b && b == c) { // 3 matching symbols
            return a.getPoints();
        } else if (a == b || b == c || a == c) { // 2 matching
            LibertyBellSymbols matched = (a == b || a == c) ? a : b;
            return matched.getPoints() / 2;

        }
        return 0;
    }

    public LibertyBellSymbols[] spinAndApply(boolean saveImmediately) {
        LibertyBellSymbols[] res = spin();
        System.out.println(getOutput(res));
        int payout = calculatePayout(res);
        System.out.println("Won " + payout + " points");
        if (payout > 0) {
            user.addPoints(payout * 2);
        }
        user.savePoints();
        return res;
    }

    public String getOutput(LibertyBellSymbols[] res) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LibertyBellSymbols symbol : res) {
            stringBuilder.append("[").append(symbol.getDisplayName()).append("] ");
        }
        return stringBuilder.toString();
    }

    public int getPoints() {
        return user.getPoints();
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
}