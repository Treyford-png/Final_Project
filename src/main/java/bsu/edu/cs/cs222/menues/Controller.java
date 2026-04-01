package bsu.edu.cs.cs222.menues;

import bsu.edu.cs.cs222.games.faro.RunFaro;
import bsu.edu.cs.cs222.games.horse_race.RunHorseRace;
import bsu.edu.cs.cs222.games.liberty_bell.RunLibertyBell;
import bsu.edu.cs.cs222.games.roulette.RunRoulette;
import bsu.edu.cs.cs222.games.vingt_un.RunVingtUn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {

    @FXML
    private void runGame1(ActionEvent event) {
        System.out.println("Running Faro...");
        new Thread(() -> RunFaro.main(null)).start();
    }

    @FXML
    private void runGame2(ActionEvent event) {
        System.out.println("Running Horse Race...");
        new Thread(() -> RunHorseRace.main(null)).start();
    }

    @FXML
    private void runGame3(ActionEvent event) {
        System.out.println("Running Liberty Bell...");
        new Thread(() -> RunLibertyBell.main(null)).start();
    }

    @FXML
    private void runGame4(ActionEvent event) {
        System.out.println("Running Roulette...");
        new Thread(() -> RunRoulette.main(null)).start();
    }

    @FXML
    private void runGame5(ActionEvent event) {
        System.out.println("Running VingtUn...");
        new Thread(() -> RunVingtUn.main(null)).start();
    }
}