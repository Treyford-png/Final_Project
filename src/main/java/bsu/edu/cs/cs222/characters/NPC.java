package bsu.edu.cs.cs222.characters;

public class NPC {
    private final String name;
    private final String id;
    private double points;
    private final int vuTarget;
    private final int chanceToCheat;

    public NPC(String name, String id, double points, int vuTarget, int chanceToCheat) {
        this.name = name;
        this.id = id;
        this.points = points;
        this.vuTarget = vuTarget;
        this.chanceToCheat = chanceToCheat;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getPoints() {
        return points;
    }

    public double addPoints(double pointsToAdd) {
        points += pointsToAdd;
        return points;
    }

    public double subtractPoints(double pointsToSubtract) {
        points -= pointsToSubtract;
        return points;
    }

    public int getTarget() {
        return vuTarget;
    }

    public int getChanceToCheat() {
        return chanceToCheat;
    }
}
