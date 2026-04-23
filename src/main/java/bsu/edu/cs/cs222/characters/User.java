package bsu.edu.cs.cs222.characters;

import bsu.edu.cs.cs222.characters.NPCs.AllNPCs;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class for the user/player
 * Passed between games to allow for shared and stored data
 *
 * @author Holden Hankins
 */
public class User {
    private final String username;
    private final String password;
    private int points;
    private AllNPCs allNPCS;
    Path filePath;

    public User(String username, String password, int points) {
        this.username = username;
        this.password = password;
        this.points = points;
        filePath = Paths.get("src/main/resources/users/" + username + ".csv");
        allNPCS = new AllNPCs(username);
    }

    /**
     * Takes a csv file, splits it, and uses that to create a user
     * Created to be used for testing
     *
     * @param filePath location of user file
     */
    public User(Path filePath) {
        try {
            BufferedReader userFileReader = Files.newBufferedReader(filePath);
            String userLine;
            userLine = userFileReader.readLine();
            String[] parsedUserLine = userLine.split(",");
            username = parsedUserLine[0];
            password = parsedUserLine[1];
            points = Integer.parseInt(parsedUserLine[2]);
            allNPCS = new AllNPCs(username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
    }

    /**
     * Takes the wager by default out of their account so they cannot bet over their points
     * @param wager amount of points
     * @return if possible
     */
    public boolean makeWager(int wager) {
        if (wager > points) {
            return false;
        }
        points -= wager;
        return true;
    }

    /**
     * We ball
     * @return points bet
     */
    public int allIn() {
        int allPoints = points;
        points = 0;
        return allPoints;
    }

    /**
     * Saves all points to local CSV file
     */
    public void savePoints() {
        File file = new File("src/main/resources/users/" + username + ".csv");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/main/resources/users/" + username + ".csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(username + "," + password + "," + points);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public AllNPCs getAllNPCS() {
        return allNPCS;
    }
}
