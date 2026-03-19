package bsu.edu.cs.cs222.games.liberty_bell;

public enum LibertyBellSymbols {
    LIBERTY_BELL("Libery Bell", 1000),
    HORSESHOE("Horseshoe", 500),
    DIAMOND("Diamond",200),
    SPADE("Spade",100),
    HEART("Heart",50);

    private final String displayName;
    private final int points;

    LibertyBellSymbols(String displayName, int points) {
        this.displayName = displayName;
        this.points = points;
    }

    public String getDisplayName() { return displayName; }
    public int getPoints() { return points; }
}
