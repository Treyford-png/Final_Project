package bsu.edu.cs.cs222.games.horse_race;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.faro.TableController;
import bsu.edu.cs.cs222.helpers.HelpersFX;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HorsePickerController implements Initializable {
    private User user;
    private HorseRaceController hrc;
    private final String[] horseList = new String[]{"1: Gibby", "2: Billy", "3: Belisarius", "4: Unicycle",
            "5: Canvas Discussion Thread"};
    @FXML private ChoiceBox<String> horseDropdown;
    @FXML private Spinner<Integer> betSpinner;

    public HorsePickerController() {

    }

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

    public void race() throws IOException {
        int wager = betSpinner.getValue();
        int horseNum;
        String text = horseDropdown.getValue();
        if (text.equals("1: Gibby")) {
            horseNum = 1;
        } else if (text.equals("2: Billy")) {
            horseNum = 2;
        } else if (text.equals("3: Belisarius")) {
            horseNum = 3;
        } else if (text.equals("4: Unicycle")){
            horseNum = 4;
        } else {
            horseNum = 5;
        }
        Stage stage = (Stage) horseDropdown.getScene().getWindow();
        stage.close();
        hrc.horseRace(horseNum, wager);
    }
}
