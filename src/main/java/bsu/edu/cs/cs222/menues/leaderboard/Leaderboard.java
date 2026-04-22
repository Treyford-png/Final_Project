package bsu.edu.cs.cs222.menues.leaderboard;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.menues.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

public class Leaderboard {
    private final ArrayList<LeaderboardNode> leaderboard; // username, points
    private final String userFileName; // ONLY MODIFIED IN TESTS
    private boolean corrupted; // USED FOR DEBUGGING ONLY
    @FXML public TextArea textArea;
    private User user; // Saves user to readd back to main menu


    public Leaderboard() {
        leaderboard = new ArrayList<>();
        userFileName = "list_of_users";
        corrupted = false;
    }

    @FXML
    public void initialize() {
        populateLeaderboard();
        textArea.setText(output());
    }

    /**
     * Takes in a file name other than the default "list_of_users.csv"
     * USED FOR TESTING ONLY
     * @param userFileName name of user list file
     */
    public Leaderboard(String userFileName) {
        this.userFileName = userFileName;
        leaderboard = new ArrayList<>();
        corrupted = false;
        populateLeaderboard();
        textArea = new TextArea(output());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void populateLeaderboard() {
        try {
            // Gets all usernames, which is stored locally in list_of_users.csv
            Path path = Path.of("src/main/resources/users/" + userFileName + ".csv");
            BufferedReader reader = Files.newBufferedReader(path);
            String AllUsersLine = reader.readLine();
            String[] usersArray = AllUsersLine.split(",");

            // Finds each individual user's file and adds their name and points to the array
            for (String username : usersArray) {
                Path userFilePath = Path.of("src/main/resources/users/" + username + ".csv");
                BufferedReader userReader = Files.newBufferedReader(userFilePath);
                String userLine = userReader.readLine();
                String[] userSplitLine = userLine.split(",");
                leaderboard.add(new LeaderboardNode(username, Integer.parseInt(userSplitLine[2])));
                userReader.close();
            }

            // Sorts array by points in ascending order
            leaderboard.sort(Comparator.comparing(LeaderboardNode::getPoints).reversed());
            reader.close();
        } catch (IOException e) {
            corrupted = true;
        }
    }

    public void updateLeaderboard() {
        leaderboard.clear();
        populateLeaderboard();
    }

    /**
     * Output is in format "[username] - [points]\n" ... n
     * @return leaderboard in string format
     */
    public String output() {
        updateLeaderboard();
        StringBuilder output = new StringBuilder();
        for (LeaderboardNode node : leaderboard) {
            System.out.println(node);
            output.append(node).append("\n");
        }
        return output.substring(0, output.length() - 1); // truncates last \n char
    }

    public boolean isCorrupted() {
        return corrupted;
    }

    public void back() throws IOException {
        FXMLLoader mmLoader = new FXMLLoader(getClass().getResource("/fxmls/menues/main_menu.fxml"));
        Parent root = mmLoader.load();
        Stage stage = (Stage) textArea.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        MainMenuController mmController = mmLoader.getController();
        mmController.setUser(user);
    }
}
