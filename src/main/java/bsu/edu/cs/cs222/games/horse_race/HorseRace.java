package bsu.edu.cs.cs222.games.horse_race;
import bsu.edu.cs.cs222.characters.User;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Runs logic for horse race game
 * Mainly used for test cases
 *
 * @author JJ Owsley
 * @author Holden Hankins
 */
public class HorseRace {
    private final Horse[] horses;
    private final Random random;
    private final User user;
    private final JTextArea outputArea;
    private final JFrame frame;

    public HorseRace(User user) {
        this.user = user;
        random = new Random();

        //Create GUI window
        frame = new JFrame("Horse Race");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBackground(Color.decode("#d6b588"));


        frame.add(new JScrollPane(outputArea));
        frame.setVisible(true);

        // Populate horses
        Horse horse1 = new Horse(1, 50);
        Horse horse2 = new Horse(2, 50);
        Horse horse3 = new Horse(3, 50);
        Horse horse4 = new Horse(4, 50);
        Horse horse5 = new Horse(5, 50);
        horses = new Horse[]{horse1, horse2, horse3, horse4, horse5};
    }

    /**
     * Primary logic for horse race
     * Gets guess and wager, runs horses, then calculates points
     */
    public void horseRace() {

        // User guesses which horse will win
        int userGuess = getUserGuess();
        while (userGuess < 0 || userGuess > 5) { // Invalid Input
            userGuess = getUserGuess();
        }
        if (userGuess == 0) { // User cancels
            frame.dispose();
            return;
        }

        // User places wager
        int wager = getUserWager();
        while (wager == -1) { // Invalid input
            wager = getUserWager();
        }
        user.makeWager(wager);

        // Race itself
        int winningHorse = runRace();
        calcPoints(winningHorse, userGuess, wager);

        // As horse races were normally just one round, there is no need to loop
        println("That completes today's entertainment. Please join us again for more racing\n");
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
     *
     * @return int of winning horse
     */
    public int runRace() {
        boolean raceOver = false;
        int winningHorse = -1;
        println("AND THEY'RE OFF!\n");
        StringBuilder roundDisplay;

        // Loops until winner found
        while (!raceOver) {
            roundDisplay = new StringBuilder();
            for (Horse horse : horses) {
                horse.turn(random.nextInt(100)); // Randomizes if horse moves on its turn
                roundDisplay.append(buildTrackString(horse));
                if (horse.hasWon()) {
                    winningHorse = horse.getName();
                    raceOver = true;
                    break;
                }
            }
            setText(roundDisplay.toString());
            try {
                // Prevents entire race from being displayed at once
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("error");
            }
        }

        return winningHorse;
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
            println("Congratulations, pardner! Horse " + winner + " won!");
            user.addPoints(wager + (wager * 5));
            println("You just won " + (wager * 5) + " points!");
        } else { // Lost
            println("Ouch! Your horse wasn't quite fast enough this time.");
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
        lane.append("Horse ").append(horse.getName()).append(": ");
        for (int i = 0; i < Horse.TRACK_LEN; i++) {
            if (i == horse.getPosition()) {
                lane.append("\uD83C\uDFC7 ");
            } else {
                lane.append(". ");
            }
        }
        return lane.toString();
    }

    /**
     * Replaces output area to text
     */
    private void setText(String text) {
        outputArea.setText(text);
        outputArea.setCaretPosition(0);
    }

    /**
     * Prints the GUI
     */
    private void println(String text) {
        outputArea.append(text + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }
}