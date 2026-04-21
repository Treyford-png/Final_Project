package bsu.edu.cs.cs222.games.faro;

import bsu.edu.cs.cs222.helpers.HelpersFX;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TableController {
    private FaroPlayer player;
    @FXML
    private TextArea betsArea;
    @FXML
    private Spinner<Integer> pointsSpinner;
    @FXML
    private Label nameLabel;
    @FXML
    private ChoiceBox<String> cardsDropdown;
    private final String[] cards = new String[]{"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};

    public TableController() {
    }

    @FXML
    public void initialize() {
        try {
            cardsDropdown.getItems().addAll(cards);
        } catch (Exception e) {
            // NPC table
        }
    }

    public void setPlayer(FaroPlayer player) {
        this.player = player;
        nameLabel.setText(player.getName());
        showBets();

        try {
            HelpersFX.setFactory(pointsSpinner, player.getPoints());
        } catch (Exception e) {
             // NPC table
        }
    }

    public void addBetPlayer() {
        player.addToWager(cardsDropdown.getValue(), pointsSpinner.getValue());
    }

    public void showBets() {
        betsArea.setText(player.getWagers());
    }

    public void close() {
        Stage stage = (Stage) betsArea.getScene().getWindow();
        stage.close();
    }
}