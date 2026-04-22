package bsu.edu.cs.cs222.games.faro;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class CasekeepController {
    @FXML public TextArea casekeepText;
    private Casekeep casekeep;

    public CasekeepController() {

    }

    public void setCasekeep(Casekeep casekeep) {
        this.casekeep = casekeep;
        setCasekeepText();
    }

    public void setCasekeepText() {
        casekeepText.setText(casekeep.output());
    }

    public void close() {
        Stage stage = (Stage) casekeepText.getScene().getWindow();
        stage.close();
    }
}
