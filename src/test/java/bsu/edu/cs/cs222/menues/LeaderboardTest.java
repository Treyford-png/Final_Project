package bsu.edu.cs.cs222.menues;

import bsu.edu.cs.cs222.menues.leaderboard.Leaderboard;
import org.junit.jupiter.api.Test;

public class LeaderboardTest {
    @Test
    public void testLeaderboard() {
        Leaderboard leaderboard = new Leaderboard("test_user_list");
        String expectedOutput = "TestUser2 - 5000\nTestUser1 - 1000\nTestUser3 - 200\n";
        System.out.println(leaderboard.output());
        assert(leaderboard.output().equals(expectedOutput));
    }

    @Test
    public void testIsCorrupted() {
        Leaderboard leaderboard = new Leaderboard("test_user_list");
        assert !leaderboard.isCorrupted();

        leaderboard = new Leaderboard("nonexistent_file");
        assert leaderboard.isCorrupted();
    }
}