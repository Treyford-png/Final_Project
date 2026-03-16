package bsu.edu.cs.cs222.games.roulette;

import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class Roulette {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random number = new Random();

        String slot = "";
        String colorGuess;
        String numberGuess;

        int count = 1;
        int points = 1000;
        int bet;

        HashMap<String, String> colorMap = new HashMap<>();

        colorMap.put("0", "green");
        colorMap.put("00", "green");

        colorMap.put("1", "red");
        colorMap.put("2", "black");
        colorMap.put("3", "red");
        colorMap.put("4", "black");
        colorMap.put("5", "red");
        colorMap.put("6", "black");
        colorMap.put("7", "red");
        colorMap.put("8", "black");
        colorMap.put("9", "red");
        colorMap.put("10", "black");
        colorMap.put("11", "black");
        colorMap.put("12", "red");
        colorMap.put("13", "black");
        colorMap.put("14", "red");
        colorMap.put("15", "black");
        colorMap.put("16", "red");
        colorMap.put("17", "black");
        colorMap.put("18", "red");
        colorMap.put("19", "red");
        colorMap.put("20", "black");
        colorMap.put("21", "red");
        colorMap.put("22", "black");
        colorMap.put("23", "red");
        colorMap.put("24", "black");
        colorMap.put("25", "red");
        colorMap.put("26", "black");
        colorMap.put("27", "red");
        colorMap.put("28", "black");
        colorMap.put("29", "black");
        colorMap.put("30", "red");
        colorMap.put("31", "black");
        colorMap.put("32", "red");
        colorMap.put("33", "black");
        colorMap.put("34", "red");
        colorMap.put("35", "black");
        colorMap.put("36", "red");

        while (count <= 1) {

            System.out.println("You currently have " + points + " points.");
            System.out.println("How many points would you like to bet?");
            bet = input.nextInt();
            input.nextLine();

            points -= bet;

            System.out.println("Guess a color for betting (red or black):");
            colorGuess = input.nextLine();

            System.out.println("Type a number if you want to guess a number, or type 'no' to just bet the color:");
            numberGuess = input.nextLine();

            int randomInt = number.nextInt(38);

            if (randomInt == 37) {
                slot = "00";
            } else {
                slot = Integer.toString(randomInt);
            }

            String color = colorMap.get(slot);

            System.out.println("> The computer chose " + slot + " which is " + color);

            boolean success = false;

            if (numberGuess.equalsIgnoreCase("no")) {
                if (colorGuess.equalsIgnoreCase(color)) {
                    success = true;
                    points += bet * 2;
                }
            } else {
                if (colorGuess.equalsIgnoreCase(color) && numberGuess.equals(slot)) {
                    success = true;
                    points += bet * 7;
                }
            }

            if (success) {
                System.out.println("Succeeded");
            } else {
                System.out.println("Failed");
            }

            System.out.println("You now have " + points + " points.");

            count += 1;
        }
    }
}