package bsu.edu.cs.cs222.menues.leaderboard;

/**
 * Individual node for each leaderboard position
 * I am doing this out of protest over not having structs in Java
 *
 * @author Holden Hankins
 */
public record LeaderboardNode(String username, int points) {

    /**
     * @return "[name] - [points]"
     */
    @Override
    public String toString() {
        return username + " - " + points;
    }
}