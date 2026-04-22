package bsu.edu.cs.cs222.games.roulette;
import bsu.edu.cs.cs222.characters.*;

import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class Roulette {
    private final User user;
    private Scanner input;
    private HashMap<String, String> colorMap;

    public Roulette(User user) { // creating the roulette hashmap per session per user
        this.user = user;
        populateRouletteWheel();
    }

    public void runRoulette() { // Requests if the user wants to keep playing or not.
        for (int i = 0; i < 1000; i++) {
            rouletteGame();
            input = new Scanner(System.in);
            System.out.print("Do you want to spin again? (1) yes, (0) no: ");
            if (!(input.nextInt() == 1)) {
                return;
            }
        }
    }

    public String getColor(String key) {
        return colorMap.get(key);
    }

    public void populateRouletteWheel() { // Hashmap that assigns colors to corresponding numbers
        colorMap = new HashMap<>();
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
    }

    public String spinWheel() { // randomly selects number from hashmap
        Random random = new Random();
        int rand = random.nextInt(38);
        if (rand == 37) {
            return "00";
        } else {
            return  Integer.toString(rand);
        }
    }

    public void rouletteGame() { //Runs the game based on user input
        input = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Random number = new Random();

        String slot = "";
        String colorGuess;
        String numberGuess;

        int count = 1;
        int points = user.getPoints();
        int bet;
        while (count <= 1) {

            System.out.println("You currently have " + points + " points.");
            System.out.println("How many points would you like to bet?");
            bet = input.nextInt();
            input.nextLine();

            points -= bet;

            System.out.println("Guess a color for betting (red or black):");
            colorGuess = input.nextLine();

            System.out.println("Type a number if you want to guess a number, or type 'x' to just bet the color: ");
            numberGuess = input.nextLine();

            int winnings = calcResults(spinWheel(), numberGuess, colorGuess, bet);

            if (winnings > 0) {
                System.out.println("Succeeded");
            } else {
                System.out.println("Failed");
            }

            points += winnings;

            System.out.println("You now have " + points + " points.");

            count += 1;
            savePoints(points);
            System.out.println();
        }
    }

    public int calcResults(String number, String numberGuess, String colorGuess, int bet) {
        System.out.println(number + ", " + numberGuess + ", " + colorGuess + ", " + bet);

        // Green 0 and 00 cases
        if (number.equals("0") || number.equals("00")) {
            return -bet;
        }

        String color = colorMap.get(number);
        boolean colorMatch = colorGuess.equalsIgnoreCase(color);
        boolean numberChosen = !numberGuess.equalsIgnoreCase("x");
        boolean numberMatch = numberGuess.equals(number);

        // Number bet x2
        if (!numberChosen) {
            if (colorMatch) {
                System.out.println("color");
                return bet * 2;
            }
        } else  {
            if (numberMatch) {
                System.out.println("number");
                return bet * 7;
            }
        }
        // loss case
        System.out.println("lost");
        return bet - (bet * 2);
    }

    public void savePoints(int points) { // Saves points for user
        user.addPoints(points - user.getPoints());
        user.savePoints();
        System.out.println("Your current points: " + user.getPoints());

    }
}