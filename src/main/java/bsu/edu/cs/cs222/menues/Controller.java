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
        System.out.println("Running Game 1...");
        new Thread(() -> RunFaro.main(null)).start();
    }

    @FXML
    private void runGame2(ActionEvent event) {
        System.out.println("Running Game 2...");
        new Thread(() -> RunHorseRace.main(null)).start();
    }

    @FXML
    private void runGame3(ActionEvent event) {
        System.out.println("Running Game 3...");
        new Thread(() -> RunLibertyBell.main(null)).start();
    }

    @FXML
    private void runGame4(ActionEvent event) {
        System.out.println("Running Game 4...");
        new Thread(() -> RunRoulette.main(null)).start();
    }

    @FXML
    private void runGame5(ActionEvent event) {
        System.out.println("Running Game 5...");
        new Thread(() -> RunVingtUn.main(null)).start();
    }
}