package bsu.edu.cs.cs222.games.roulette;

import java.util.Random;
import java.util.Scanner;

public class Roulette {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random number = new Random();
        String slot = "";
        String guess;

        int count = 1;
        int correctCount = 0;

        while (count <= 1) {
            int randomInt = number.nextInt(38);

            if (randomInt == 37) {
                slot = "00";
            } else {
                slot = Integer.toString(randomInt);
            }

            System.out.println(count + ". Which number has the computer chosen?");
            guess = input.nextLine();
            System.out.println("> The computer chose " + slot);

            count += 1;
            if (guess.equals(slot)) {
                correctCount += 1;
            }
        }

        System.out.println("You got " + correctCount + " correct");
    }
}