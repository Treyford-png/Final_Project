package bsu.edu.cs.cs222.games.horse_race;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.helpers.HelpersFX;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Creates a frame to pick your horse and amount to bet on
 * Implements initializable to make ChoiceBox work
 * User user
 * HorseRaceController hrc - controller of main scene
 * String[] HorseList - 5 horses you can bet on
 */
public class HorsePickerController implements Initializable {
    private User user; // user is used, no clue why a warning is here
    private HorseRaceController hrc;
    private final String[] horseList = new String[]{"1: Gibby", "2: Billy", "3: Belisarius", "4: Unicycle",
            "5: Canvas Discussion Thread"};

    @FXML private ChoiceBox<String> horseDropdown;
    @FXML private Spinner<Integer> betSpinner;

    public HorsePickerController() {}

    /**
     * Adds all
     * @param url built in URL call
     * @param resourceBundle built in ResourceBundle call
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        horseDropdown.getItems().addAll(horseList);
    }

    public void setUser(User user) {
        this.user = user;
        HelpersFX.setFactory(betSpinner, user.getPoints());
    }

    public void setHorseRaceController(HorseRaceController hrc) {
        this.hrc = hrc;
    }

    /**
     * Gets bet and returns it to the HRController before closing program
     * @throws IOException close program
     */
    public void race() throws IOException {
        // get horse and bet
        int wager = betSpinner.getValue();
        int horseNum;
        String text = horseDropdown.getValue();
        horseNum = switch (text) {
            case "1: Gibby" -> 1;
            case "2: Billy" -> 2;
            case "3: Belisarius" -> 3;
            case "4: Unicycle" -> 4;
            default -> 5;
        };

        // Close stage
        Stage stage = (Stage) horseDropdown.getScene().getWindow();
        stage.close();

        // Transfer horse and amount (both as ints)
        hrc.horseRace(horseNum, wager);
    }
}
