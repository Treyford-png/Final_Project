package bsu.edu.cs.cs222.games.horse_race;
import bsu.edu.cs.cs222.characters.User;

import java.util.Random;
import java.util.Scanner;

public class HorseRace {
    private final Horse[] horses; // Array that holds all race horses
    private final Random random;
    private final User user;
    Scanner scanner;

    public HorseRace(User user) {
        this.user = user;
        random = new Random();

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
        while (userGuess < 1 || userGuess > 5) { // Invalid Input
            userGuess = getUserGuess();
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
        System.out.println("That completes today's entertainment. Please join us again for more racing\n");
    }

    /**
     * Asks the user which horse to wager on
     * @return int of winning horse
     */
    public int getUserGuess() {
        scanner = new Scanner(System.in);
        System.out.print("Welcome to the Horse Race!");
        System.out.print("\nWhich horse will win? (1, 2, 3, 4, or 5): ");
        return scanner.nextInt();
    }

    /**
     * Asks user for amount to wager
     * @return wager, -1 if invalid
     */
    public int getUserWager() {
        scanner = new Scanner(System.in);
        int points = user.getPoints();
        System.out.print("You have " + points + " points. How much would you like to wager: ");
        int wager = scanner.nextInt();
        if (wager >= points) { // Wager too high
            System.out.print("You only have " + points +". Would you like to go all in?\n(0) no, (1) yes: ");
            wager = scanner.nextInt() == 1 ? points : -1;
        }
        return wager;
    }

    /**
     * Loops horse racing logic until one reaches the end
     * Horse on top wins in ties
     * @return int of winning horse
     */
    public int runRace() {
        boolean raceOver = false;
        int winningHorse = -1;
        System.out.println("AND THEY'RE OFF!\n");

        // Loops until winner found
        while (!raceOver) {
            System.out.println();
            for (Horse horse : horses) {
                horse.turn(random.nextInt(100)); // Randomizes if horse moves on its turn
                if (horse.hasWon()) {
                    winningHorse = horse.getName();
                    raceOver = true;
                    break;
                }
            }
            try {
                // Prevents entire race from being displayed at once
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return winningHorse;
    }

    /**
     * Readds wager and adds 5:1 payout to user
     * @param winner of the race
     * @param userGuess guessed horse to win
     * @param wager points wagered by user
     */
    public void calcPoints(int winner, int userGuess, int wager) {
        if (userGuess == winner) { // Won
            System.out.println("Congratulations, pardner! Horse " + winner + " won!");
            user.addPoints(wager + (wager * 5));
            System.out.println("You just won " + (wager * 5) + " points!");
        } else { // Lost
            System.out.println("Ouch! Your horse wasn't quite fast enough this time.");
        }

        // Make changes permanent
        user.savePoints();
        System.out.println("That leaves you with " + user.getPoints() + " points");
    }
}