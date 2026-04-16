package bsu.edu.cs.cs222.characters.NPCs;

import java.io.Serializable;

public class NPC implements Serializable {
    private final String id;
    private final String name;
    private int points;
    private final int vuTarget;
    private boolean isOut;

    public NPC(String id, String name, int points, int vuTarget) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.vuTarget = vuTarget;
        isOut = false;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int addPoints(int pointsToAdd) {
        points = Math.max(points += pointsToAdd, 0);
        if (points == 0) {
            isOut = true;
        }
        return points;
    }

    public int getVUTarget() {
        return vuTarget;
    }

    private boolean isOut() {
        return isOut();
    }
}
