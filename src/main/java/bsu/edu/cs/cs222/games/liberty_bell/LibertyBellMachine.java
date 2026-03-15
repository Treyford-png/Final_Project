package bsu.edu.cs.cs222.games.liberty_bell;
import java.util.Objects;
import java.util.Random;

public class LibertyBellMachine {
    private final LibertyBellSymbols[] strip;
    private final Random rng;

    private int credits = 0;
    private int bet = 1;

    public LibertyBellMachine() {
        this(new Random(), LibertyBellSymbols.values());
    }

    public LibertyBellMachine(Random rng, LibertyBellSymbols[] strip) {
        this.rng = Objects.requireNonNull(rng, "rng");
        if (strip == null )
    }
}
