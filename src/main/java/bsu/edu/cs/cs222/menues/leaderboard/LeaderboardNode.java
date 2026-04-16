package bsu.edu.cs.cs222.menues.leaderboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeaderboardNode {
    private final String username;
    private final int points;

    public LeaderboardNode(String username, int points) {
        this.username = username;
        this.points = points;

    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return username + " - " + points;
    }
}