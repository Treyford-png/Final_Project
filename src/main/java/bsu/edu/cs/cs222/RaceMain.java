package bsu.edu.cs.cs222;

import java.util.Random;
import java.util.Scanner;

public class RaceMain {
    public static void main(String[] args) {

        Random rand = new Random();

        Horse horse1 = new Horse(1, 50);
        Horse horse2 = new Horse(2, 50);
        Horse horse3 = new Horse(3, 50);
        Horse horse4 = new Horse(4, 50);
        Horse horse5 = new Horse(5, 50);

        int userGuess = getUserGuess();

        boolean raceOver = false;
        int winningHorse = -1;

        System.out.println("HORSE RACE STARTS!\n");

        while (!raceOver) {

            horse1.turn(rand.nextInt(100) + 1);
            horse2.turn(rand.nextInt(100) + 1);
            horse3.turn(rand.nextInt(100) + 1);
            horse4.turn(rand.nextInt(100) + 1);
            horse5.turn(rand.nextInt(100) + 1);

            System.out.println();

            if(horse1.hasWon()) {
                winningHorse = horse1.getName();
                raceOver = true;
            }
            else if(horse2.hasWon()) {
                winningHorse = horse2.getName();
                raceOver = true;
            }
            else if(horse3.hasWon()) {
                winningHorse = horse3.getName();
                raceOver = true;
            }
            else if(horse4.hasWon()) {
                winningHorse = horse4.getName();
                raceOver = true;
            }
            else if(horse5.hasWon()) {
                winningHorse = horse5.getName();
                raceOver = true;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (userGuess == winningHorse) {
            System.out.println("Congratulations, partner! Horse " + winningHorse + " won!");
        } else {
            System.out.println("Oof! You bet on the wrong horse, partner! Horse " + winningHorse + " won.");
        }

        System.out.println("HORSE RACE FINISHED!\n");
    }

    public static int getUserGuess() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Welcome to the Horse Race!");
        System.out.println("Which horse will win? (1, 2, 3, 4, or 5");

        return scanner.nextInt();
    }
}
