package bsu.edu.cs.cs222.games.horse_race;
import bsu.edu.cs.cs222.characters.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HorseRaceController {
    private final Horse[] horses; // Array that holds all race horses
    private final Random random;
    private User user;
    private JTextArea outputArea;
    private JFrame frame;
    private int winningHorse;
    private int userGuess;
    private int wager;
    @FXML private Label path1;
    @FXML private Label path2;
    @FXML private Label path3;
    @FXML private Label path4;
    @FXML private Label path5;

    public HorseRaceController() {
        this.user = null;
        random = new Random();

        // Populate horses
        Horse horse1 = new Horse(1, 50);
        Horse horse2 = new Horse(2, 50);
        Horse horse3 = new Horse(3, 50);
        Horse horse4 = new Horse(4, 50);
        Horse horse5 = new Horse(5, 50);
        horses = new Horse[]{horse1, horse2, horse3, horse4, horse5};
    }

    public void setUser(User user) {
        this.user = user;
        horseRace();
    }

    /**
     * Primary logic for horse race
     * Gets guess and wager, runs horses, then calculates points
     */
    public void horseRace() {

        // User guesses which horse will win
        userGuess = getUserGuess();
        while (userGuess < 0 || userGuess > 5) { // Invalid Input
            userGuess = getUserGuess();
        }
        if (userGuess == 0) { // User cancels
            return;
        }

        // User places wager
        wager = getUserWager();
        while (wager == -1) { // Invalid input
            wager = getUserWager();
        }
        user.makeWager(wager);

        // Race itself
        runRace();

        // As horse races were normally just one round, there is no need to loop
        //println("That completes today's entertainment. Please join us again for more racing\n");
    }

    /**
     * Asks the user which horse to wager on
     *
     * @return int of winning horse
     */
    public int getUserGuess() {
        String input = JOptionPane.showInputDialog(
                null,
                "Welcome to the Horse Race!",
                "\nWhich horse will win? (1, 2, 3, 4, or 5): ");
        try {
            if (input == null) {
                return 0;
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Asks user for amount to wager
     *
     * @return wager, -1 if invalid
     */
    public int getUserWager() {
        int points = user.getPoints();
        String input = JOptionPane.showInputDialog(
                null,
                "You have " + points + " points. How much would you like to wager: "
        );
        try {
            int wager = Integer.parseInt(input);
            if (wager >= points) { // Wager too high
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "You only have " + points + ". Would you like to go all in?\n(0) no, (1) yes: "
                );
                return (choice == JOptionPane.YES_OPTION) ? points : -1;
            }
            return wager;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Loops horse racing logic until one reaches the end
     * Horse on top wins in ties
     */
    public void runRace() {
        for (Horse horse : horses) {
            horse.moveToStart();
        }
        //println("AND THEY'RE OFF!\n");

        // Loops until winner found
        int time = 100;
        Timer timer = new Timer();
        winningHorse = -1;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    runHorses();

                    if (winningHorse != -1) {
                        timer.cancel();
                        calcPoints(winningHorse, userGuess, wager);
                    }
                });
            }
        }, 0, 100);
    }

    public void runHorses() {
        for (Horse horse : horses) {
            horse.turn(random.nextInt(100));
            println(horse);
            if (horse.hasWon()) {
                winningHorse = horse.getName();
                return; // Stop immediately on first winner
            }
        }
    }

    /**
     * Readds wager and adds 5:1 payout to user
     *
     * @param winner    of the race
     * @param userGuess guessed horse to win
     * @param wager     points wagered by user
     */
    public void calcPoints(int winner, int userGuess, int wager) {
        if (userGuess == winner) { // Won
            //println("Congratulations, pardner! Horse " + winner + " won!");
            user.addPoints(wager + (wager * 5));
            //println("You just won " + (wager * 5) + " points!");
        } else { // Lost
            //println("Ouch! Your horse wasn't quite fast enough this time.");
        }

        // Make changes permanent
        user.savePoints();
        System.out.println("That leaves you with " + user.getPoints() + " points");
    }

    /**
     * 🐴 Track display
     */
    public String buildTrackString(Horse horse) {
        StringBuilder lane = new StringBuilder();
        lane.append(horse.getName()).append(": ");
        for (int i = 0; i < horse.TRACK_LEN; i++) {
            if (i == horse.getPosition()) {
                lane.append("\uD83C\uDFC7");
            } else {
                lane.append(". ");
            }
        }
        return lane.toString();
    }

    //Printing the GUI
    private void println(Horse horse) {
        switch (horse.getName()) {
            case 1:
                path1.setText(buildTrackString(horse));
                break;
            case 2:
                path2.setText(buildTrackString(horse));
                break;
            case 3:
                path3.setText(buildTrackString(horse));
                break;
            case 4:
                path4.setText(buildTrackString(horse));
                break;
            case 5:
                path5.setText(buildTrackString(horse));
        }
    }
}