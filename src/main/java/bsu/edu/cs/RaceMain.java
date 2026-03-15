package bsu.edu.cs;

import java.util.Random;

public class RaceMain {
    public static void main(String[] args) {

        Random rand = new Random();

        Horse horse1 = new Horse(1, 50);
        Horse horse2 = new Horse(1, 50);
        Horse horse3 = new Horse(1, 50);

        boolean raceOver = false;

        System.out.println("HORSE RACE STARTS!\n");

        while (!raceOver) {

            int flip1 = rand.nextInt(100) + 1;
            int flip2 = rand.nextInt(100) + 1;
            int flip3 = rand.nextInt(100) + 1;

            horse1.turn(flip1);
            horse2.turn(flip2);
            horse3.turn(flip3);

            System.out.println();

            if(horse1.hasWon() || horse2.hasWon() || horse3.hasWon()) {
                raceOver = true;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("HORSE RACE FINISHED!\n");
    }
}
