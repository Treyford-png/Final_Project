package bsu.edu.cs.cs222.games.liberty_bell;

public enum LibertyBellSymbols {
    LIBERTY_BELL("Libery Bell", 10000),
    HORSESHOE("Horseshoe", 5000),
    DIAMOND("Diamond",2000),
    SPADE("Spade",1000),
    HEART("Heart",500);

    private final String displayName;
    private final int points;

    LibertyBellSymbols(String displayName, int points) {
        this.displayName = displayName;
        this.points = points;
    }

    public String getDisplayName() { return displayName; }
    public int getPoints() { return points; }
}
