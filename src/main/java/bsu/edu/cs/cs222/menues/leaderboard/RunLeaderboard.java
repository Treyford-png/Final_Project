package bsu.edu.cs.cs222.menues.leaderboard;

/**
 * Independently runs launcher
 * Is used for testing and debugging
 *
 * @author Holden Hankins
 */
public class RunLeaderboard {
    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        System.out.println(leaderboard.output());
    }
}