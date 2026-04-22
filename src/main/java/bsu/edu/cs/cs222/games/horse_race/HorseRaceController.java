package bsu.edu.cs.cs222.games.horse_race;
import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.menues.MainMenuController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HorseRaceController {
    private final Horse[] horses; // Array that holds all race horses
    private final Random random;
    private User user;
    private int winningHorse;
    private int userGuess;
    private int wager;
    @FXML
    private Label path1, path2, path3, path4, path5;

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

    public void setUser(User user) throws IOException {
        this.user = user;
        createHorsePicker();
    }

    /**
     * Primary logic for horse race
     * Gets guess and wager, runs horses, then calculates points
     */
    public void horseRace(int userGuess, int wager) throws IOException {

        // User guesses which horse will win
        this.userGuess = userGuess;
        this.wager = wager;
        user.makeWager(wager);

        // Race itself
        runRace();
    }

    /**
     * Loops horse racing logic until one reaches the end
     * Horse on top wins in ties
     */
    public void runRace() throws IOException {
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
                        try {
                            resetToMainMenu();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
            JOptionPane.showMessageDialog(null, "Congratulations, pardner! Horse " + winner + " won!");
            user.addPoints(wager + (wager * 5));
            JOptionPane.showMessageDialog(null, "You just won " + (wager * 5) + " points!");
        } else { // Lost
            JOptionPane.showMessageDialog(null, "Ouch! Your horse wasn't quite fast enough this time.");
        }

        // Make changes permanent
        user.savePoints();
        JOptionPane.showMessageDialog(null, "That leaves you with " + user.getPoints() + " points");
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

    public void createHorsePicker() throws IOException {
        FXMLLoader pickerLoader = new FXMLLoader(getClass().getResource("/fxmls/pick_your_horse.fxml"));
        Parent root = pickerLoader.load();
        Stage stage2 = new Stage();
        stage2.setTitle("Pick Your Horse");
        stage2.setScene(new Scene(root));
        stage2.show();

        // Passes user and HorseRaceController into new stage
        HorsePickerController hpc = pickerLoader.getController();
        hpc.setUser(user);
        hpc.setHorseRaceController(this);
    }

    private void resetToMainMenu() throws IOException {
        FXMLLoader mmLoader = new FXMLLoader(getClass().getResource("/fxmls/main_menu.fxml"));
        Parent root = mmLoader.load();
        Stage stage = (Stage) path1.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        MainMenuController mmController = mmLoader.getController();
        mmController.setUser(user);
    }
}