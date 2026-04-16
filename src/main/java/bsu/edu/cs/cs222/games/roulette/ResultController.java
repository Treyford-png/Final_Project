package bsu.edu.cs.cs222.games.roulette;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultController {

    @FXML private Label landedLabel;
    @FXML private Label outcomeLabel;
    @FXML private Label pointsLabel;

    public void setData(String number, String color, String outcome, int points) {

        landedLabel.setText("Landed: " + number + " (" + color + ")");
        outcomeLabel.setText(outcome);
        pointsLabel.setText("Points: " + points);
    }
}