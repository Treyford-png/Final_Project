package bsu.edu.cs.cs222.games.liberty_bell;

import bsu.edu.cs.cs222.characters.User;

import java.util.Scanner;

/**
 * Handles a text version of Liberty Bell slots
 *
 * @author Laura Romero
 */
public class LibertyBellGame {
    User user;
    LibertyBellMachine libertyBell;
    Scanner lbScanner;

    public LibertyBellGame(User user) {
        this.user = user;
        this.libertyBell = new LibertyBellMachine();
    }

    /**
     * Upon prompt, spins reels for player
     */
    public void playGame() {
        int playAgain;
        for (int i = 0; i < 1000; i++) {
            spin();
            lbScanner = new Scanner(System.in);
            System.out.print("Would you like to play again?\n(1) yes, (2) no: ");
            playAgain = lbScanner.nextInt();
            if (playAgain != 1) {
                return;
            }
        }
    }

    /**
     * Gets the user's bet and spins the wheel
     */
    public void spin() {
        int wager = getWager();
        while (wager == -1) {
            wager = getWager();
        }
        user.makeWager(wager);
        //libertyBell.spinAndApply(true);
    }

    /**
     * Gets the user's bet
     *
     * @return wager/bet
     */
    public int getWager() {
        lbScanner = new Scanner(System.in);
        System.out.print("Please enter a new wager: ");
        int wager = lbScanner.nextInt();
        if (wager >= user.getPoints()) {
            System.out.print("Would you like to go all in for " + user.getPoints() + "?\n(1) Yes, (0) No: ");
            int input = lbScanner.nextInt();
            return (input == 1) ? user.getPoints() : -1;
        }
        return wager;
    }
}
