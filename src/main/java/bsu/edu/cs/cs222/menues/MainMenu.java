package bsu.edu.cs.cs222.menues;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.faro.Faro;
import bsu.edu.cs.cs222.games.horse_race.HorseRace;
import bsu.edu.cs.cs222.games.liberty_bell.LibertyBellGame;
import bsu.edu.cs.cs222.games.roulette.Roulette;
import bsu.edu.cs.cs222.games.vingt_un.VingtUn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;



public class MainMenu {
    private Scanner menuScanner;
    private final User user;

    public MainMenu(User user) {
        this.user = user;
    }

    /**
     * Loops main menu, allowing player to play multiple games
     */
    public void runMainMenu() {
        welcome();
        int menuReturn = mainMenu();
        while (menuReturn != -1) {
            menuReturn = mainMenu();
        }
    }

    public void welcome() {
        System.out.println("\nWelcome to Frontier Games!!!\nThe home of the best games of the Wild West");
    }

    public int mainMenu() {
        menuScanner = new Scanner(System.in);
        System.out.println("\nWhich game would you like to play, sir?" +
                "\n(1) Faro" +
                "\n(2) Horse Race" +
                "\n(3) Liberty Bell Slots" +
                "\n(4) Roulette" +
                "\n(5) Vingt-Un" +
                "\n(6) Get points" +
                "\n(0) Exit");
        int choice = menuScanner.nextInt();
        launchGame(choice);
        if (choice == 0) {
            return - 1;
        }
        return 0;
    }

    public void launchGame(int choice) {
        switch (choice) {
            case 0: // Exit
                System.out.println("So long partner!");
                break;
            case 1: // Faro
                Faro faro = new Faro(user);
                faro.runGame();
                break;
            case 2: // Horse Race
                //HorseRace horseRace = new HorseRace(user);
                //horseRace.horseRace();
                break;
            case 3: // Liberty Bell
                LibertyBellGame libertyBell = new LibertyBellGame(user);
                libertyBell.playGame();
                break;
            case 4: // Roulette
                Roulette roulette = new Roulette(user);
                roulette.runRoulette();
                break;
            case 5: // Vingt-Un
                VingtUn vingtUn = new VingtUn(user);
                vingtUn.runGame();
                break;
            case 6: // Username and points
                menuScanner = new Scanner(System.in);
                System.out.println(user.getUsername() + user.getPoints());
                System.out.println("Enter any number to exit");
                int exit = menuScanner.nextInt();
                break;
            default: // Other
                System.out.println("Invalid input");
        }
    }
}
