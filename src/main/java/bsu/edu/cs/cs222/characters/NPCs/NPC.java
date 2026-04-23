package bsu.edu.cs.cs222.characters.NPCs;

import java.io.Serializable;

/**
 * Serializable object that allows for NPC data to be stored.
 * id - findable 3-char set that is used for calling
 * name
 * points
 * USED FOR ITERATION 4:
 * vuTarget - amount that will be bet by that player
 * isOut - if the NPC is out of money
 *
 * @author Holden Hankins
 */
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
